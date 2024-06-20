package com.yan.capstone_sewain.admintoko

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.LoginActivity
import com.yan.capstone_sewain.R
import com.yan.capstone_sewain.profile.UserProfil

class VerfiyTokoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verfiy_toko)

        val done = findViewById<Button>(R.id.btn_R)

        done.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}