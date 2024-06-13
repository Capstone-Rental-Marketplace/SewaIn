const express = require('express');
const router = express.Router();
const multer = require('multer');
const path = require('path');
const db = require('../database');

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

// Route to get all items
router.get('/', (req, res) => {
  db.query('SELECT * FROM items', (err, results) => {
    if (err) {
      res.status(500).send(err.message);
      return;
    }
    res.json(results);
  });
});

// Route to get a single item by ID
router.get('/:id', (req, res) => {
  db.query('SELECT * FROM items WHERE id = ?', [req.params.id], (err, result) => {
    if (err) {
      res.status(500).send(err.message);
      return;
    }
    if (result.length > 0) {
      res.json(result[0]);
    } else {
      res.status(404).send('Item not found');
    }
  });
});

// Route to add a new item with an image
router.post('/', upload.single('image'), (req, res) => {
  const { name, price, description, stock } = req.body;
  const imageUrl = req.file ? `/uploads/${req.file.filename}` : null;

  db.query(
    'INSERT INTO items (name, price, description, stock, imageUrl) VALUES (?, ?, ?, ?, ?)',
    [name, price, description, stock, imageUrl],
    (err, result) => {
      if (err) {
        res.status(500).send(err.message);
        return;
      }
      res.status(201).json({ id: result.insertId, name, price, description, stock, imageUrl });
    }
  );
});

// Route to update an item by ID
router.put('/:id', upload.single('image'), (req, res) => {
  const itemId = req.params.id;
  const { name, price, description, stock } = req.body;
  const imageUrl = req.file ? `/uploads/${req.file.filename}` : null;

  db.query(
    'UPDATE items SET name=?, price=?, description=?, stock=?, imageUrl=? WHERE id=?',
    [name, price, description, stock, imageUrl, itemId],
    (err, result) => {
      if (err) {
        res.status(500).send(err.message);
        return;
      }
      if (result.affectedRows === 0) {
        res.status(404).send('Item not found');
        return;
      }
      res.json({ id: itemId, name, price, description, stock, imageUrl });
    }
  );
});

// Route to delete an item by ID
router.delete('/:id', (req, res) => {
  const itemId = req.params.id;

  db.query('DELETE FROM items WHERE id = ?', [itemId], (err, result) => {
    if (err) {
      res.status(500).send(err.message);
      return;
    }
    if (result.affectedRows === 0) {
      res.status(404).send('Item not found');
      return;
    }
    res.send(`Item with ID ${itemId} has been deleted`);
  });
});

module.exports = router;
