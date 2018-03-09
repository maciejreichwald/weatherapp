package com.rudearts.weatherapp.ui.main

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rudearts.weatherapp.R
import com.rudearts.weatherapp.databinding.CityItemBinding
import com.rudearts.weatherapp.model.local.City


class CityAdapter(context:Context) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var cities = emptyList<City>()
    private var onClick:(city:City) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = createBinding(parent)
        return ViewHolder(binding)
    }

    private fun createBinding(parent: ViewGroup): CityItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.city_item, parent, false)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = cities[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun updateCities(items:List<City>) {
        cities = items
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener:(city:City) -> Unit) {
        onClick = listener
    }

    inner class ViewHolder(private val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.city = city
            binding.root.setOnClickListener { onClick?.invoke(city) }
            binding.executePendingBindings()
        }
    }
}