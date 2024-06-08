// logic autentikasi pengguna: signup dan login

// mengimpor model users untuk berinteraksi dengan tabel users di database
const users = require("../database/models/users");
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');

const generateToken = (payload) => {
    return jwt.sign(payload, process.env.JWT_SECRET_KEY, {
        expiresIn: process.env.JWT_EXPIRES_IN
    });
};


// signup
const signup = async (req, res, next) => {
    const body = req.body;

    // validasi input
    if (!body.username || !body.email || !body.password) {
        return res.status(400).json({
            status: 'Fail',
            message: 'Please provide username, email, and password'
        });
    }

    // cek apakah email telah terdaftar
    const existingUser = await users.findOne({ where: { email: body.email } });
    if (existingUser) {
        return res.status(400).json({
            status: 'Fail',
            message: 'Email already in use'
        });
    }

    // meng-hash (untuk melindungi data sensitif) password sebelum disimpan ke database
    const hashedPassword = await bcrypt.hash(body.password, 10);

    // menambahkan user baru ke tabel users
    const newUser = await users.create({
        username: body.username,
        email: body.email,
        // password: body.password
        password: hashedPassword
    });

    const result = newUser.toJSON();

    // hapus password dari hasil yang dikembalikan sebelum mengirim respons
    delete result.password;
    delete result.deleteAt;

    // generate token untuk pengguna baru
    result.token = generateToken({
        id: result.id
    });

    // kirim respon sukses dengan data pengguna baru
    return res.status(201).json({
        status: 'Success',
        message:'Sign Up Successful',
        data: result
    });
};

// login
const login = async (req, res, next) => {
    const{ email, password } = req.body;
    
    // validasi input
    if(!email || !password){
        return res.status(400).json({
            status: 'Fail',
            message: 'Please provide email and password'
        });
    }

    // mencari data user di database berdasarkan email
    const result = await users.findOne({ where: { email } });
    if(!result){
        return res.status(401).json({
            status: 'Fail',
            message: 'Incorrect email or password'
        });
    }

    // membandingkan password yang diinput dengan yang ada di database
    const isPasswordMatch = await bcrypt.compare(password, result.password);
    if(!isPasswordMatch){
        return res.status(401).json({
            status: 'Fail',
            message: 'Incorrect email or password'
        });
    }

    // jika email dan password cocok, kirim token sebagai respons
    const token = generateToken({ id: result.id });
    return res.json({
        status: 'Success',
        message:'Login Successful',
        token
    });
};

// Logout
const logout = (req, res) => {
    // Biasanya Anda juga akan menghapus token dari sisi klien (misalnya, localStorage)
    res.status(200).json({
        status: 'Success',
        message: 'Logout successful'
    });
};

module.exports = {
    signup,
    login,
    logout
};