package com.yan.capstone_sewain.data.repository

import com.yan.capstone_sewain.data.retrofit.RetrofitInstance
import com.yan.capstone_sewain.data.response.SignupResponse
import com.yan.capstone_sewain.data.response.LoginResponse
import com.yan.capstone_sewain.data.response.LoginRequest
import com.yan.capstone_sewain.data.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun signup(user: UserModel, onResult: (SignupResponse?) -> Unit) {
        val request = RetrofitInstance.api.signup(user)
        request.enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun login(email: String, password: String, onResult: (LoginResponse?) -> Unit) {
        val request = RetrofitInstance.api.login(LoginRequest(email, password))
        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }
}
