from fastapi import FastAPI
import pandas as pd
import numpy as np
import tensorflow as tf
print(tf.__version__)
import os; os.environ['TF_ENABLE_ONEDNN_OPTS'] = '0'
import uvicorn
from tensorflow.keras.models import load_model
import nest_asyncio  # Import modul nest_asyncio

# Terapkan perubahan untuk event loop asyncio
nest_asyncio.apply()

app = FastAPI()

# Load CSV files
rental_df = pd.read_csv('https://storage.googleapis.com/sewain_project/rental_dataset.csv')
vehicle_df = pd.read_csv('https://storage.googleapis.com/sewain_project/vehicle_dataset.csv')

# Merge rental_df and vehicle_df based on id_vehicle
merged_df = pd.merge(rental_df, vehicle_df, left_on='id_vehicle', right_on='id_vehicle', how='left')

def encode_features(data):
    data = data.copy()  # Membuat salinan data agar tidak memodifikasi data asli
    data = data[['id_user', 'id_vehicle', 'rating','colors', 'vehicle_type', 'brand']]

    # Bagian 1: Encoding fitur untuk df_user
    user_columns = ['id_user', 'id_vehicle', 'rating']  # Kolom yang tidak diencode
    user_data = data[user_columns].copy()  # Salin kolom-kolom yang tidak diencode
    user_data['data_features'] = data['colors'] + '_' + data['vehicle_type'] + '_' + data['brand']  # Gabungkan fitur 'colors', 'vehicle_type', dan 'brand'
    user_features = user_data['data_features'].str.get_dummies(sep='_')  # Encoding fitur
    df_user = pd.concat([user_data.drop(columns={'data_features', 'id_vehicle'}), user_features], axis=1)  # Gabungkan dengan DataFrame asli
    
    # Bagian 2: Encoding fitur untuk df_item
    item_columns = ['id_user']  # Kolom yang tidak diencode
    item_data = data.drop(columns=item_columns).copy()  # Salin kolom-kolom yang tidak diencode
    item_data['data_features'] = data['colors'] + '_' + data['vehicle_type'] + '_' + data['brand']  # Gabungkan fitur 'colors', 'vehicle_type', dan 'brand'
    item_features = item_data['data_features'].str.get_dummies(sep='_')  # Encoding fitur
    df_item = pd.concat([item_data.drop(columns=['data_features']), item_features], axis=1)  # Gabungkan dengan DataFrame asli
    
    return df_user, df_item

# Prepare data
encoded_df_user, encoded_df_item = encode_features(merged_df)
num_item_columns = encoded_df_item[2:]

# Load the TensorFlow model
model = load_model("rental_recommendation_model.h5")

# Define FastAPI routes
@app.get("/")
async def root():
    return {"message": "Hello World"}
@app.get("/recommendations/")
async def get_recommendations(user_id: int, vehicle_id: int, colors: str, vehicle_type: str, brand: str, rating: float):
    new_data = {
        'id_user': user_id,
        'colors': colors,
        'vehicle_type': vehicle_type,
        'rating': rating, 
        'brand': brand
    }

    new_df = pd.DataFrame([new_data])

    # Membuat DataFrame baru dengan kolom-kolom yang sesuai dengan DataFrame encoded_df_user
    new_df_encoded = pd.DataFrame(columns=encoded_df_user.columns)

    # Mengisi DataFrame baru dengan nilai-nilai baru
    for col in new_df_encoded.columns:
        if col == 'id_user':
            new_df_encoded[col] = new_df['id_user']
        elif col in ['colors', 'vehicle_type', 'brand']:
            new_df_encoded[col] = new_df[col]
        else:
            # Menggunakan nilai rating jika nama kolom cocok dengan colors, vehicle_type, dan brand
            if (col in new_df.columns) and (col != 'id_user'):
                condition = (encoded_df_user['colors'] == new_df['colors'].iloc[0]) & \
                            (encoded_df_user['vehicle_type'] == new_df['vehicle_type'].iloc[0]) & \
                            (encoded_df_user['brand'] == new_df['brand'].iloc[0])
                new_df_encoded[col] = new_df['rating'] if condition.any() else 0  # 0 jika tidak ada kesesuaian


    # Make predictions
    predictions = model.predict([new_df_encoded, encoded_df_item[num_item_columns]])
    top_indices = np.argsort(predictions.flatten())[-7:][::-1]

    # Get recommended vehicles
    recommended_vehicles = merged_df.iloc[top_indices]

    recommendations = []
    for i, (_, row) in enumerate(recommended_vehicles.iterrows()):
        recommendations.append({
            "rank": i + 1,
            "id_rental": row['id_rental'],
            "vehicle_name": row['vehicle_name'],
            "color": row['colors'],
            "vehicle_type": row['vehicle_type'],
            "vehicle_brand": row['brand']
        })

    return {
        "user_id": user_id,
        "recommendations": recommendations
    }


if __name__ == "__main__":
    uvicorn.run(app, host='0.0.0.0', port=int(os.environ.get('PORT', 5001)))