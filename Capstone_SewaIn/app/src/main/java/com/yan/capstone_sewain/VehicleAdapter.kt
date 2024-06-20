package com.yan.capstone_sewain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Vehicle(val imageResId: Int, val name: String, val description: String)

class VehicleAdapter(private val vehicles: List<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.vehicleImage.setImageResource(vehicle.imageResId)
        holder.vehicleName.text = vehicle.name
        holder.vehicleDescription.text = vehicle.description
    }

    override fun getItemCount(): Int = vehicles.size

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vehicleImage: ImageView = itemView.findViewById(R.id.vehicle_image)
        val vehicleName: TextView = itemView.findViewById(R.id.vehicle_name)
        val vehicleDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}