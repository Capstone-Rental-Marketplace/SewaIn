package com.yan.capstone_sewain.Api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

    interface ApiService {
        @POST("auth/signup")
        fun signup(@Body user: User): Call<ApiResponse>

        @GET("auth/login")
        fun login(@Body user: User): Call<ApiResponse>
    }

