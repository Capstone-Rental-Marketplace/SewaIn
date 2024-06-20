package com.yan.capstone_sewain

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yan.capstone_sewain.data.viewmodel.UserViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var fullnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullnameEditText = findViewById(R.id.fullname)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirmpass)
        registerButton = findViewById(R.id.btn_R)

        registerButton.setOnClickListener {
            val name = fullnameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (password == confirmPassword) {
                userViewModel.signup(name, email, password)
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

        userViewModel.signupResponse.observe(this, Observer { response ->
            response?.let {
                if (response.message.contains("User created successfully")) {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    Log.d("SignupActivity", "Registration successful: ${response.message}")
                    // Handle successful registration, e.g., navigate to another activity
                    // val intent = Intent(this, LoginActivity::class.java)
                    // startActivity(intent)
                } else {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                    Log.e("SignupActivity", "Registration failed: ${response.message}")
                }
            } ?: run {
                Toast.makeText(this, "Registration failed: response is null", Toast.LENGTH_SHORT).show()
                Log.e("SignupActivity", "Registration failed: response is null")
            }
        })
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}
