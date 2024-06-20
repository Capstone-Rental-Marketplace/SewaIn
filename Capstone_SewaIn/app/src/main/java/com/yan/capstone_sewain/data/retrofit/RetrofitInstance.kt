package com.yan.capstone_sewain.data.retrofit

import com.yan.capstone_sewain.data.api.UserApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://sewain-api-user-5b25hvndba-et.a.run.app/auth/"

    val api: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}
