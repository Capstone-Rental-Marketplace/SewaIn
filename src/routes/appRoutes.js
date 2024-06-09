const express = require('express');
const authHandler = require('../handlers/authHandler');
const userHandler = require('../handlers/userHandler');
const searchHandler = require('../handlers/searchHandler');
const authMiddleware = require('../middlewares/authMiddleware');

const router = express.Router();

// Auth routes
router.post('/auth/signup', authHandler.signup);
router.post('/auth/login', authHandler.login);
router.post('/auth/logout', authHandler.logout);

// User routes
router.get('/user/profile', authMiddleware, userHandler.getUserProfile);
router.put('/user/profile', authMiddleware, userHandler.updateUserProfile);
router.delete('/user/profile', authMiddleware, userHandler.deleteUserAccount);

// Search routes
router.get('/search', searchHandler.searchPlaces);

module.exports = router;
