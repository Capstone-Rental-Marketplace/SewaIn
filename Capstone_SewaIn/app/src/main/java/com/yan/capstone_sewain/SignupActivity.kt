package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.yan.capstone_sewain.database.Database

class SignupActivity : AppCompatActivity() {

    private lateinit var registerButton: Button
    private lateinit var registerTextView: TextView
    private lateinit var fullnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPassEditText: EditText

    private lateinit var dbHelper: Database


    private fun validateInputs(
        fullname: String,
        email: String,
        password: String,
        confirmPass: String
    ): Boolean {
        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
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
            val fullname = fullnameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPass = confirmPassEditText.text.toString().trim()

            if (validateInputs(fullname, email, password, confirmPass)) {
                if (password == confirmPass) {
                    val result = dbHelper.addUser(fullname, email, password)
                    if (result != -1L) {
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}