package com.yan.capstone_sewain.Api

import com.yan.capstone_sewain.Api.model.LoginRequest
import com.yan.capstone_sewain.Api.model.LoginResponse
import com.yan.capstone_sewain.Api.model.RegisterResponse
import com.yan.capstone_sewain.Api.model.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}


