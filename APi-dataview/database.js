const mysql = require('mysql2');

// Replace these values with your cloud MySQL database credentials
const db = mysql.createConnection({
  host: '34.101.73.111',
  user: 'sewain',
  password: 'sewain2024',
  database: 'sewain_db'
});

db.connect((err) => {
  if (err) {
    console.error('Error connecting to the database:', err.message);
    return;
  }
  console.log('Connected to the database.');
});

module.exports = db;
