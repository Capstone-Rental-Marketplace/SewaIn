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

        addBarang.setOnClickListener {
            val intent = Intent(this, TambahProduk::class.java)
            startActivity(intent)
        }
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}
