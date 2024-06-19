from fastapi import FastAPI
from pydantic import BaseModel
import pandas as pd
import numpy as np
import tensorflow as tf
from sklearn.preprocessing import StandardScaler, MinMaxScaler

# Initialize FastAPI app
app = FastAPI()

# Define the input model for the API
class UserInput(BaseModel):
    user_id: int
    colors: str
    vehicle_type: str
    brand: str
    rating: float

# Load the pre-trained model
model = tf.keras.models.load_model('/content/rental_recommendation_model.h5')

# Load data and preprocess
rental_df = pd.read_csv('https://storage.googleapis.com/sewain_project/rental_dataset.csv')
vehicle_df = pd.read_csv('https://storage.googleapis.com/sewain_project/vehicle_dataset.csv')
merged_df = rental_df.merge(vehicle_df, on='id_vehicle')

def encode_features(data):
    data = data.copy()
    data = data[['id_user', 'id_vehicle', 'rating', 'colors', 'vehicle_type', 'brand']]
    user_columns = ['id_user', 'id_vehicle', 'rating']
    user_data = data[user_columns].copy()
    user_data['data_features'] = data['colors'] + '_' + data['vehicle_type'] + '_' + data['brand']
    user_features = user_data['data_features'].str.get_dummies(sep='_')
    df_user = pd.concat([user_data.drop(columns={'data_features', 'id_vehicle'}), user_features], axis=1)
    item_columns = ['id_user']
    item_data = data.drop(columns=item_columns).copy()
    item_data['data_features'] = data['colors'] + '_' + data['vehicle_type'] + '_' + data['brand']
    item_features = item_data['data_features'].str.get_dummies(sep='_')
    df_item = pd.concat([item_data.drop(columns={'data_features', 'colors', 'vehicle_type', 'brand'}), item_features], axis=1)
    return df_user, df_item

encoded_df_user, encoded_df_item = encode_features(merged_df)
num_item_columns = encoded_df_item.columns[2:]
num_user_columns = encoded_df_user.columns[2:]

for i in range(2, len(encoded_df_user.columns)):
    feature_column = encoded_df_user.columns[i]
    encoded_df_user[feature_column] = encoded_df_user.apply(lambda row: row['rating'] if row[feature_column] == 1 else np.nan, axis=1)

df_user_avg = encoded_df_user.groupby('id_user')[num_user_columns].mean().reset_index()
df_user_avg.fillna(0, inplace=True)
encoded_df_user = pd.merge(encoded_df_user, df_user_avg, how='left', on='id_user')
num_columns_to_keep = 1 + len(num_user_columns)
num_columns_to_drop = len(encoded_df_user.columns) - num_columns_to_keep
encoded_df_user.drop(columns=encoded_df_user.columns[1:num_columns_to_drop + 1], inplace=True)
encoded_df_user.columns = ['id_user'] + num_user_columns.tolist()

scaler_user = StandardScaler()
scaler_item = StandardScaler()
scaler_user.fit(encoded_df_user[num_user_columns])
scaler_item.fit(encoded_df_item[num_item_columns])
encoded_df_item[num_item_columns] = scaler_item.transform(encoded_df_item[num_item_columns])

@app.post("/recommend")
async def recommend(user_input: UserInput):
    # Prepare new data for prediction
    new_data = {
        'id_user': user_input.user_id,
        'colors': user_input.colors,
        'vehicle_type': user_input.vehicle_type,
        'rating': user_input.rating,
        'brand': user_input.brand
    }

    # Create a new DataFrame with the same columns as encoded_df_user
    new_df_encoded = pd.DataFrame(columns=encoded_df_user.columns)
    new_df_encoded.loc[0] = 0
    new_df_encoded['id_user'] = user_input.user_id
    if user_input.colors in new_df_encoded.columns:
        new_df_encoded[user_input.colors] = user_input.rating
    if user_input.vehicle_type in new_df_encoded.columns:
        new_df_encoded[user_input.vehicle_type] = user_input.rating
    if user_input.brand in new_df_encoded.columns:
        new_df_encoded[user_input.brand] = user_input.rating

    new_df_encoded[num_user_columns] = scaler_user.transform(new_df_encoded[num_user_columns])
    new_user = np.tile(new_df_encoded[num_user_columns], (encoded_df_item.shape[0], 1))

    # Make predictions
    predictions = model.predict([new_user, encoded_df_item[num_item_columns]])

    rating = merged_df['rating'].values
    scaler = MinMaxScaler((-1, 1))
    scaler.fit(rating.reshape(-1, 1))
    rating = scaler.transform(rating.reshape(-1, 1))
    predictions = scaler.inverse_transform(predictions)
    sorted_predictions = np.argsort(predictions, axis=0)[::-1].flatten()
    sorted_item = merged_df.index.to_numpy()[sorted_predictions].flatten()

    data_test = merged_df.copy()
    data_test = data_test[['id_user', 'id_rental', 'id_vehicle', 'rating', 'vehicle_name', 'colors', 'number_of_seats']]

    dic_predictions = {
        'userId': np.full((encoded_df_item.shape[0],), user_input.user_id),
        'index': merged_df.iloc[sorted_item].index,
        'predictions': predictions[sorted_predictions].flatten()
    }
    df_predictions = pd.DataFrame(dic_predictions)
    df_predictions.set_index('index', inplace=True)
    df_predictions = pd.merge(df_predictions, data_test, how='left', left_index=True, right_index=True).reset_index(drop=True)
    df_predictions.drop_duplicates(subset=['id_vehicle'], inplace=True)
    df_predictions.drop(columns=['id_user', 'rating'], inplace=True)
    df_predictions.rename(columns={'id_user_x': 'id_user'}, inplace=True)
    df_predictions.reset_index(drop=True, inplace=True)

    # Prepare the recommendations
    recommendations = []
    for i, row in df_predictions.head(10).iterrows():
        recommendations.append({
            "rank": i + 1,
            "id_rental": row['id_rental'],
            "vehicle_name": row['vehicle_name'],
            "color": row['colors'],
            "vehicle_type": row['vehicle_type'],
            "vehicle_brand": row['brand']
        })

    return {"recommendations": recommendations}

# To run the FastAPI app
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
