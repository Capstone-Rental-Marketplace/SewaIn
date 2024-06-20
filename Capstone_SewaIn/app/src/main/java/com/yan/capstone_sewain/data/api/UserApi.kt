package com.yan.capstone_sewain.data.api

import com.yan.capstone_sewain.data.response.LoginResponse
import com.yan.capstone_sewain.data.response.LoginRequest
import com.yan.capstone_sewain.data.response.SignupResponse
import com.yan.capstone_sewain.data.model.UserModel
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface UserApi {
    @POST("signup")
    fun signup(@Body user: UserModel): Call<SignupResponse>

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}

