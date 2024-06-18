package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.Api.ApiResponse
import com.yan.capstone_sewain.Api.RetrofitClient
import com.yan.capstone_sewain.Api.User
import com.yan.capstone_sewain.admintoko.RegisterToko
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgetPasswordTextView: TextView
    private lateinit var registerTextView: TextView
    private lateinit var registerTextView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.btn_L)
        forgetPasswordTextView = findViewById(R.id.forgetpassword)
        registerTextView = findViewById(R.id.txt_signup)
        registerTextView1 = findViewById(R.id.txt_signup_toko)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                login(email, password)
            }
        }

        forgetPasswordTextView.setOnClickListener {
            Toast.makeText(this, "Forget Password clicked", Toast.LENGTH_SHORT).show()
        }

        registerTextView.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        registerTextView1.setOnClickListener {
            val intent = Intent(this, RegisterToko::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        val user = User(email, "", password) // Ubah sesuai dengan model User yang digunakan
        RetrofitClient.api.login(user).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Menutup activity login setelah berhasil login
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login failed: ${response.errorBody()?.string()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
