package com.yan.capstone_sewain.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yan.capstone_sewain.LoginActivity
import com.yan.capstone_sewain.R
import com.yan.capstone_sewain.admintoko.ProfilToko

class RegisteredUserProfil : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profil)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        val btnEdit = findViewById<Button>(R.id.btn_edit_profil)
        val logOut = findViewById<ImageView>(R.id.logout)
        val profilToko = findViewById<LinearLayout>(R.id.lihat_toko)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }

        logOut.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditProfil::class.java)
            startActivity(intent)
        }

        profilToko.setOnClickListener {
            val intent = Intent(this, ProfilToko::class.java)
            startActivity(intent)
        }

    }
}
