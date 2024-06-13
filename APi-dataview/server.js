const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const multer = require('multer');
const path = require('path');
const db = require('./database');
const itemsRouter = require('./routes/items'); // Mengimpor file rute items

const app = express();
const port = 3000;

app.use(bodyParser.json());
app.use(cors());

// Static folder to serve uploaded images
app.use('/uploads', express.static('uploads'));

// Configure multer for file storage
const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, 'uploads/');
  },
  filename: function (req, file, cb) {
    cb(null, Date.now() + path.extname(file.originalname));
  }
});

const upload = multer({ storage: storage });

// Menggunakan router untuk rute items
app.use('/api/items', itemsRouter);

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
