package com.yan.capstone_sewain.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yan.capstone_sewain.LoginActivity
import com.yan.capstone_sewain.R
import com.yan.capstone_sewain.Vehicle
import com.yan.capstone_sewain.VehicleAdapter
import com.yan.capstone_sewain.admintoko.RegisterToko

class UserProfil : AppCompatActivity() {

    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicles: List<Vehicle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profil)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        val btnEdit = findViewById<Button>(R.id.btn_edit_profil)
        val regToko = findViewById<LinearLayout>(R.id.regist_toko)
        val logOut = findViewById<ImageView>(R.id.logout)

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

        btnBack.setOnClickListener {
            onBackPressed()
        }

        logOut.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditProfil::class.java)
            startActivity(intent)
        }

        regToko.setOnClickListener {
            val intent = Intent(this, RegisterToko::class.java)
            startActivity(intent)
        }
    }
}
