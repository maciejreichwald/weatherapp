package com.rudearts.weatherapp.ui.main

import com.rudearts.weatherapp.model.LoadingState
import com.rudearts.weatherapp.model.local.City

interface MainContract {

    interface View {
        fun updateCities(cities: List<City>)
        fun updateLoadingState(state:LoadingState)
        fun showMessage(message: String)
        fun hideKeyboard()
    }

    interface Presenter {
        fun search(city:String)
    }
}