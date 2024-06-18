package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.Api.ApiResponse
import com.yan.capstone_sewain.Api.RetrofitClient
import com.yan.capstone_sewain.Api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private fun Any.enqueue(callback: Callback<ApiResponse>) {

}

class SignupActivity : AppCompatActivity() {

    private lateinit var registerButton: Button
    private lateinit var registerTextView: TextView
    private lateinit var fullnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPassEditText: EditText

    private fun validateInputs(
        username: String,
        email: String,
        password: String,
        confirmPass: String
    ): Boolean {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        registerButton = findViewById(R.id.btn_R)
        registerTextView = findViewById(R.id.txt_login)
        fullnameEditText = findViewById(R.id.fullname)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        confirmPassEditText = findViewById(R.id.confirmpass)

        registerTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val username = fullnameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPass = confirmPassEditText.text.toString().trim()

            if (validateInputs(username, email, password, confirmPass)) {
                if (password == confirmPass) {
                    register(username,email, password)
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun register(username: String, email: String, password: String) {
        val user = User(username, email, password)
        RetrofitClient.api.signup(user).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@SignupActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("SignupActivity", "Registration failed: $errorBody")
                    Toast.makeText(this@SignupActivity, "Registration failed: $errorBody", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("SignupActivity", "Error: ${t.message}", t)
                Toast.makeText(this@SignupActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
