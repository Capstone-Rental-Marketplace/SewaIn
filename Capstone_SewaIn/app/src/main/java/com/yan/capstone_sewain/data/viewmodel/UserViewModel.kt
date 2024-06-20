package com.yan.capstone_sewain.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yan.capstone_sewain.data.model.UserModel
import com.yan.capstone_sewain.data.repository.UserRepository
import com.yan.capstone_sewain.data.response.LoginResponse
import com.yan.capstone_sewain.data.response.SignupResponse

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    val signupResponse: MutableLiveData<SignupResponse?> = MutableLiveData()
    val loginResponse: MutableLiveData<LoginResponse?> = MutableLiveData()

    fun signup(username: String, email: String, password: String) {
        val user = UserModel(username, email, password)
        userRepository.signup(user) {
            signupResponse.postValue(it)
        }
    }

    fun login(email: String, password: String) {
        userRepository.login(email, password) {
            loginResponse.postValue(it)
        }
    }
}

