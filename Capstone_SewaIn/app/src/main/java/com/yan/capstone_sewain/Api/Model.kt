package com.yan.capstone_sewain.Api

data class User(val email: String, val password: String, val password1: String)
data class ApiResponse(val status: String, val message: String)
