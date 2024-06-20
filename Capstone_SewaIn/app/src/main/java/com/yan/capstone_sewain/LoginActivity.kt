package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yan.capstone_sewain.data.viewmodel.UserViewModel
import android.util.Log

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpTextView: TextView

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.btn_L)
        signUpTextView = findViewById(R.id.txt_signup)

        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            Log.d("LoginActivity", "Login button clicked with email: $email and password: $password")
            userViewModel.login(email, password)
        }

        userViewModel.loginResponse.observe(this, Observer { response ->
            response?.let {
                if (it.message.contains("Hallo!")) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    Log.d("LoginActivity", "Login successful")
                    // Navigate to MainActivity on successful login
                    val intent = Intent(this,
                        MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Log.e("LoginActivity", "Login failed: ${it.message}")
                }
            } ?: run {
                Toast.makeText(this, "Login failed: response is null", Toast.LENGTH_SHORT).show()
                Log.e("LoginActivity", "Login failed: response is null")
            }
        })
    }
}
