package com.yan.capstone_sewain

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.Api.ApiService
import com.yan.capstone_sewain.Api.model.RegisterRequest
import com.yan.capstone_sewain.Api.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    private lateinit var fullnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullnameEditText = findViewById(R.id.fullname)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirmpass)
        registerButton = findViewById(R.id.btn_R)

        retrofit = Retrofit.Builder()
            .baseUrl("https://sewain-api-user-5b25hvndba-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val fullname = fullnameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        if (password == confirmPassword) {
            val request = RegisterRequest(fullname, email, password)

            apiService.registerUser(request).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val registerResponse = response.body()!!
                        if (registerResponse.success) {
                            Toast.makeText(this@SignupActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                            // Handle successful registration
                        } else {
                            Toast.makeText(this@SignupActivity, registerResponse.message, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@SignupActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@SignupActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }
    }
}
