package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.yan.capstone_sewain.R
import com.yan.capstone_sewain.SearchActivity
import com.yan.capstone_sewain.profile.UserProfil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomMeImageView = findViewById<ImageView>(R.id.bottom_me)
        val searchView = findViewById<SearchView>(R.id.searchView)

        bottomMeImageView.setOnClickListener {
            val intent = Intent(this, UserProfil::class.java)
            startActivity(intent)
        }
        searchView.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                if (query != null) {
                    // Perform search logic here
                    // For example, update a RecyclerView adapter with search results
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search text change
                if (newText != null) {
                    // Perform live search logic here
                    // For example, filter a list based on the newText
                }
                return false
            }
        })
    }
}
