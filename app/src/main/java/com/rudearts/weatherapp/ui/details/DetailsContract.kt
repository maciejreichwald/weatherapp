package com.rudearts.weatherapp.ui.details

import com.rudearts.weatherapp.model.LoadingState
import com.rudearts.weatherapp.model.local.City
import com.rudearts.weatherapp.model.local.Weather

interface DetailsContract {

    interface View {
        fun updateView(weather: Weather)
        fun updateLoadingState(state:LoadingState)
    }

    interface Presenter {
        fun loadWeather(cityId:Long)
    }
}