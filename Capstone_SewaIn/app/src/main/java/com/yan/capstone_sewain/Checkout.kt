package com.yan.capstone_sewain

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Checkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Get the data from the intent
        val vehicleImageResId = intent.getIntExtra("VEHICLE_IMAGE", 0)
        val vehicleName = intent.getStringExtra("VEHICLE_NAME")
        val vehiclePrice = intent.getStringExtra("VEHICLE_DESCRIPTION")
        val tokoName = intent.getStringExtra("TOKO_NAME")

        // Find the views and set the data
        val vehicleImage: ImageView = findViewById(R.id.foto_barang)
        val vehicleNameTextView: TextView = findViewById(R.id.nama_kendaraan)
        val vehiclePriceTextView: TextView = findViewById(R.id.harga_kendaraan)
        val tokoNameTextView: TextView = findViewById(R.id.tv_toko)
        val totalPaymentTextView: TextView = findViewById(R.id.total_payment_value)

        vehicleImage.setImageResource(vehicleImageResId)
        vehicleNameTextView.text = vehicleName
        vehiclePriceTextView.text = vehiclePrice
        tokoNameTextView.text = tokoName
        totalPaymentTextView.text = vehiclePrice
    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}
