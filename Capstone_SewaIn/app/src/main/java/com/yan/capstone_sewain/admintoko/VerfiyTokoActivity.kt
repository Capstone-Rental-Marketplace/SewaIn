package com.yan.capstone_sewain.admintoko

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.R

class VerfiyTokoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_verfiy_toko)
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}