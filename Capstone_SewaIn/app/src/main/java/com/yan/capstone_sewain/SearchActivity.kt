package com.yan.capstone_sewain

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicles: List<Vehicle>  // Declare the list here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchView = findViewById<SearchView>(R.id.search_view)
        recyclerView = findViewById(R.id.recycler_view)

        // Initialize the sample data
        vehicles = listOf(
            Vehicle(R.drawable.nmaxlarger, "NMax1", "Rp. 200.000", "Rental Hati", 4.1f,"Rental Hati","Denpasar1"),
            Vehicle(R.drawable.nmaxlarger, "Nmax2", "Rp. 100.000", "Rental Motor", 4.7f,"Rental Hati","kota baru"),
            Vehicle(R.drawable.nmaxlarger, "Nmax3", "Rp. 300.000", "Rental Lah", 4.4f,"Rental Hati","Denpasar"),
            )

        vehicleAdapter = VehicleAdapter(vehicles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = vehicleAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform search on submit
                query?.let { searchVehicles(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Perform search on text change
                newText?.let { searchVehicles(it) }
                return false
            }
        })
    }

    private fun searchVehicles(query: String) {
        // Filter the list based on the search query
        val filteredVehicles = vehicles.filter {
            it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
        }
        vehicleAdapter = VehicleAdapter(filteredVehicles)
        recyclerView.adapter = vehicleAdapter
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }

}