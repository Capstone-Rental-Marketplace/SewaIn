package com.yan.sewain

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.yan.sewain.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.btn_1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoginListener()
    }
    private fun btnLoginListener(){
        btn_1.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}