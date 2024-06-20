package com.yan.capstone_sewain.admintoko

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yan.capstone_sewain.R
import com.yan.capstone_sewain.SearchActivity

class ProfilToko : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_toko)

        val addBarang = findViewById<FloatingActionButton>(R.id.fab)

        addBarang.setOnClickListener {
            val intent = Intent(this, TambahProduk::class.java)
            startActivity(intent)
        }
    }
    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}