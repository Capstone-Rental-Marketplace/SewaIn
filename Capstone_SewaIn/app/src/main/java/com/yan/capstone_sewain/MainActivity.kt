package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yan.capstone_sewain.profile.UserProfil

class MainActivity : AppCompatActivity() {

    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicles: List<Vehicle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomMeImageView = findViewById<ImageView>(R.id.bottom_me)
        val searchView = findViewById<SearchView>(R.id.searchView)
        recyclerView = findViewById(R.id.recycler_view)

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
                    vehicleAdapter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search text change
                if (newText != null) {
                    // Perform live search logic here
                    // For example, filter a list based on the newText
                    vehicleAdapter.filter(newText)
                }
                return false
            }
        })

        // Initialize the sample data
        vehicles = listOf(
            Vehicle(R.drawable.nmax, "Motor A", "Rp. 100.000"),
            Vehicle(R.drawable.nmax, "Motor B", "Rp. 200.000"),
            Vehicle(R.drawable.nmax, "Motor C", "Rp. 300.000"),
            Vehicle(R.drawable.nmax, "Motor D", "Rp. 300.000"),
            Vehicle(R.drawable.nmax, "Motor E", "Rp. 300.000"),
            Vehicle(R.drawable.nmax, "Motor C", "Rp. 300.000")
        )

        vehicleAdapter = VehicleAdapter(vehicles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = vehicleAdapter
    }
}
