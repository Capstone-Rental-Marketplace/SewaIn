package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailBarangUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_barang_user)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the data from the intent
        val vehicleImageResId = intent.getIntExtra("VEHICLE_IMAGE", 0)
        val vehicleName = intent.getStringExtra("VEHICLE_NAME")
        val vehicleDescription = intent.getStringExtra("VEHICLE_DESCRIPTION")
        val vehicleToko = intent.getStringExtra("VEHICLE_TOKO")
        val vehicleLocation = intent.getStringExtra("VEHICLE_LOCATION")

        // Find the views and set the data
        val vehicleImage: ImageView = findViewById(R.id.foto_barang)
        val vehicleNameTextView: TextView = findViewById(R.id.nama_kendaraan)
        val vehicleDescriptionTextView: TextView = findViewById(R.id.deskripsi_kendaraan)
        val tokoNameTextView: TextView = findViewById(R.id.nama_toko)
        val lokasiVendorTextView: TextView = findViewById(R.id.lokasi_vendor)

        vehicleImage.setImageResource(vehicleImageResId)
        vehicleNameTextView.text = vehicleName
        vehicleDescriptionTextView.text = vehicleDescription
        tokoNameTextView.text = vehicleToko
        lokasiVendorTextView.text = vehicleLocation

        // Set the click listener for the checkout button
        val checkoutButton: Button = findViewById(R.id.btn_R)
        checkoutButton.setOnClickListener {
            val intent = Intent(this, Checkout::class.java).apply {
                putExtra("VEHICLE_IMAGE", vehicleImageResId)
                putExtra("VEHICLE_NAME", vehicleName)
                putExtra("TOKO_NAME", vehicleToko)
            }
            startActivity(intent)
        }
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}
