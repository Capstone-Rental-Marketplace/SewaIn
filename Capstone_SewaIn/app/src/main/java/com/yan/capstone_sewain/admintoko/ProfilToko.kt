package com.yan.capstone_sewain.admintoko

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yan.capstone_sewain.R
import com.yan.capstone_sewain.Vehicle
import com.yan.capstone_sewain.VehicleAdapter

class ProfilToko : AppCompatActivity() {

    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicles: List<Vehicle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_toko)

        val addBarang = findViewById<FloatingActionButton>(R.id.fab)
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

        addBarang.setOnClickListener {
            val intent = Intent(this, TambahProduk::class.java)
            startActivity(intent)
        }
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}
