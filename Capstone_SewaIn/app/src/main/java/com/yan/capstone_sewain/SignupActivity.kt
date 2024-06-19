package com.yan.capstone_sewain

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
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


    }


}
