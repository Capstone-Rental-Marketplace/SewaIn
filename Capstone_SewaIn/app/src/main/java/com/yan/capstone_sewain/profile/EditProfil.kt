package com.yan.capstone_sewain.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yan.capstone_sewain.R

class EditProfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        val saveEdit = findViewById<Button>(R.id.btn_save_edit_profil)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        saveEdit.setOnClickListener {
            Toast.makeText(this, "Change saved", Toast.LENGTH_SHORT).show()
            //Logic
        }
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}