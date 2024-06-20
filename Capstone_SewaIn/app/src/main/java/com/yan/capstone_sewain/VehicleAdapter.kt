package com.yan.capstone_sewain

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Vehicle(val imageResId: Int, val name: String, val description: String, val tokoName: String, val rating: Float,val Namatoko: String, val location: String)

class VehicleAdapter(private var vehicles: List<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    fun filter(query: String) {
        vehicles = if (query.isEmpty()) {
            // Jika query kosong, tampilkan semua data
            vehicles
        } else {
            // Jika query tidak kosong, filter data berdasarkan query
            vehicles.filter {
                it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged() // Notifikasi adapter bahwa data telah berubah
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.vehicleImage.setImageResource(vehicle.imageResId)
        holder.vehicleName.text = vehicle.name
        holder.vehicleDescription.text = vehicle.description
        holder.tokoName.text = vehicle.tokoName
        holder.rating.text = vehicle.rating.toString()
        holder.locationTextView.text = vehicle.location

        // Set an OnClickListener on the item view
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailBarangUser::class.java)
            intent.putExtra("VEHICLE_IMAGE", vehicle.imageResId)
            intent.putExtra("VEHICLE_NAME", vehicle.name)
            intent.putExtra("VEHICLE_DESCRIPTION", vehicle.description)
            intent.putExtra("VEHICLE_TOKO", vehicle.tokoName)
            intent.putExtra("VEHICLE_PEMILIK_TOKO", vehicle.Namatoko)
            intent.putExtra("VEHICLE_RATING", vehicle.rating)
            intent.putExtra("VEHICLE_LOCATION", vehicle.location)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = vehicles.size

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vehicleImage: ImageView = itemView.findViewById(R.id.vehicle_image)
        val vehicleName: TextView = itemView.findViewById(R.id.vehicle_name)
        val vehicleDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tokoName: TextView = itemView.findViewById(R.id.tv_toko)
        val rating: TextView = itemView.findViewById(R.id.tv_rating)
        val locationTextView: TextView = itemView.findViewById(R.id.tv_location)
    }
}

