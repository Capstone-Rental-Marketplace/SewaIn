package com.yan.capstone_sewain.data.response

import com.yan.capstone_sewain.data.model.UserModel

data class LoginResponse(
    val message: String,
    val token: String
)

data class SignupResponse(
    val message: String,
    val user: UserModel
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class UserModel(
    val username: String,
    val email: String,
    val password: String
)
