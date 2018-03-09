package com.rudearts.weatherapp.model.local

data class Weather(
        val id: Long,
        val cod: Int,
        val main: Int,
        val description: Int,
        val icon: Int,
        val temperature: Double,
        val pressure: Double,
        val humidity: Double
)