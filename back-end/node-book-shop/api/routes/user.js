const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const bcrypt = require('bcrypt')

const User = require('../models/user')
const UserController = require('../controller/user')

router.post("/signup",UserController.user_sign_up);

router.post('/login',UserController.user_login)

router.delete("/:userId",UserController.user_delete);

router.post("/resetpassword",UserController.user_reset_pass);

module.exports = router;