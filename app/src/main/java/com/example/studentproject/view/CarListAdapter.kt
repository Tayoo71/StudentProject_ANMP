package com.example.studentproject.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentproject.databinding.CarListItemBinding
import com.example.studentproject.model.Car

class CarListAdapter(val carList: ArrayList<Car>) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    // ViewHolder class untuk menyimpan referensi view di setiap item
    class CarViewHolder(var binding: CarListItemBinding) : RecyclerView.ViewHolder(binding.root)

    // Membuat view holder baru untuk setiap item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    // Mengembalikan jumlah item dalam daftar
    override fun getItemCount(): Int {
        return carList.size
    }

    // Mengikat data ke setiap item di RecyclerView
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]

        // Mengatur data dari objek Car ke TextView yang sesuai
        holder.binding.tvCarName.text = "${car.make} ${car.model}"
        holder.binding.tvCarYearColor.text = "${car.year} - ${car.color}"
        holder.binding.tvCarPrice.text = "$${car.price}"
        holder.binding.tvCarFeatures.text = "Features: ${car.features.joinToString(", ")}"
        holder.binding.tvCarSpecs.text = "Specs: Engine: ${car.specs.engine}, Transmission: ${car.specs.transmission}, Fuel Type: ${car.specs.fuel_type}"
    }

    // Fungsi untuk memperbarui daftar mobil
    @SuppressLint("NotifyDataSetChanged")
    fun updateCarList(newCarList: ArrayList<Car>) {
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }
}
