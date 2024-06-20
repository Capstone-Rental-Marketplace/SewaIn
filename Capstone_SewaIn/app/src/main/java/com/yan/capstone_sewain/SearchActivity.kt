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
            Vehicle(R.drawable.yamahamio, "Yamaha Mio", "Rp. 65.000", "3 Monkeys Rental Bali", 4.7f,"3 Monkeys Rental Bali","Kec. Kuta Utara, Kabupaten Badung"),
            Vehicle(R.drawable.yamahafazzio, "Yamaha Fazzio", "Rp. 70.000", "cahya ubud rental motorbike", 4.4f,"cahya ubud rental motorbike","Kec. Ubud, Kabupaten Gianyar"),
            Vehicle(R.drawable.nmaxlarger, "Yamaha Nmax", "Rp. 95.000", "My Bike Rental Bali", 4.0f,"My Bike Rental Bali","Kec. Kediri, Kabupaten Tabanan"),
            Vehicle(R.drawable.yamahamio, "Yamaha Mio", "Rp. 65.000", "Agas Motorbike Rental", 4.7f,"Agas Motorbike Rental","Kec. Kediri, Kabupaten Tabanan"),
            Vehicle(R.drawable.hondapcx, "Honda PCX", "Rp. 90.000", "Bikago Motorbike Rental", 4.4f,"Bikago Motorbike Rental","Kec. Banjar, Kabupaten Buleleng"),
            Vehicle(R.drawable.hondabeat, "Honda Beat", "Rp. 60.000", "Bali Nice Rental Scooter", 4.0f,"Bali Nice Rental Scooter","Kec. Ubud, Kabupaten Gianyar"),
            Vehicle(R.drawable.hondabeat, "Honda Beat", "Rp. 60.000", "SMB Rent Bike Bali", 4.4f,"SMB Rent Bike Bali","Kec. Kuta Utara, Kabupaten Badung"),
            Vehicle(R.drawable.yamahagear, "Yamaha Gear", "Rp. 75.000", "Ketut motor rental and taxi", 4.0f,"Ketut motor rental and taxi","Kec. Nusa Penida, Kabupaten Klungkung"),
            Vehicle(R.drawable.yamahasoul, "Yamaha Soul", "Rp. 65.000", "Rental Motor Bike kadek", 4.7f,"Rental Motor Bike kadek","Kec. Abang, Kabupaten Karangasem"),
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