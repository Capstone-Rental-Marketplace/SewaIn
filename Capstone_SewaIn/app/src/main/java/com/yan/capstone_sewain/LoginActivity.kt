package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.admintoko.RegisterToko

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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        forgetPasswordTextView.setOnClickListener {
            // Handle forget password logic here
            Toast.makeText(this, "Forget Password clicked", Toast.LENGTH_SHORT).show()
        }

        registerTextView.setOnClickListener {
            // Navigate to SignupActivity
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        registerTextView1.setOnClickListener {
            // Navigate to SignupActivity
            val intent = Intent(this, RegisterToko::class.java)
            startActivity(intent)
        }
    }
}