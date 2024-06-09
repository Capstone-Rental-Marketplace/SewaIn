// fitur searcj tapi blm diintegrasikan dengan dataset tempat wisata

const db = require('../firebase');

const searchPlaces = async (req, res) => {
  const { query } = req.query;

  try {
    // Logic for searching places based on query
    // For example:
    const placesRef = db.collection('places').where('name', '==', query);
    const snapshot = await placesRef.get();

    if (snapshot.empty) {
      return res.status(404).json({ message: 'Place not found' });
    }

    const places = [];
    snapshot.forEach(doc => {
      places.push(doc.data());
    });

    res.status(200).json(places);
  } catch (error) {
    res.status(500).send('Error searching places: ' + error.message);
  }
};

module.exports = {
  searchPlaces
};
