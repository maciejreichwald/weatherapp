package com.rudearts.weatherapp.model.external

data class WeatherResponse(
        val id: Long? = null,
        val cod: Int? = null,
        val weather: List<WeatherExternal>? = null,
        val main: MainExternal? = null
)