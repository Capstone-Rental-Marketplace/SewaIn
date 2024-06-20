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
            Vehicle(R.drawable.nmaxlarger, "Yamaha Nmax", "Rp. 95.000", "My Bike Rental Bali", 4.0f,"My Bike Rental Bali","Kec. Kediri, Kabupaten Tabanan"),
            Vehicle(R.drawable.yamahamio, "Yamaha Mio", "Rp. 65.000", "Agas Motorbike Rental", 4.7f,"Agas Motorbike Rental","Kec. Kediri, Kabupaten Tabanan"),
            Vehicle(R.drawable.hondabeat, "Honda Beat", "Rp. 60.000", "SMB Rent Bike Bali", 4.4f,"SMB Rent Bike Bali","Kec. Kuta Utara, Kabupaten Badung"),
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
