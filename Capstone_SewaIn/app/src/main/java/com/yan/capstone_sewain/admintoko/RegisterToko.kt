package com.yan.capstone_sewain.admintoko

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.R
import com.yan.capstone_sewain.SearchActivity

class RegisterToko : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_toko)

        val next = findViewById<Button>(R.id.btn_R)

        next.setOnClickListener {
            val intent = Intent(this, VerfiyTokoActivity::class.java)
            startActivity(intent)
        }
    }
    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}