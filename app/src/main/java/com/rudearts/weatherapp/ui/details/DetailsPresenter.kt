package com.rudearts.weatherapp.ui.details

import com.rudearts.extentions.threadToAndroid
import com.rudearts.weatherapp.domain.LoadWeatherUseCase
import com.rudearts.weatherapp.model.LoadingState
import com.rudearts.weatherapp.model.local.City
import com.rudearts.weatherapp.model.local.Weather
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
        internal val view:DetailsContract.View?,
        internal val loadWeather:LoadWeatherUseCase) : DetailsContract.Presenter {

    override fun loadWeather(cityId:Long) {
        view?.updateLoadingState(LoadingState.LOADING)

        loadWeather.loadWeather(cityId)
                .threadToAndroid()
                .subscribe({ weather -> onWeatherLoaded(weather) })
    }

    private fun onWeatherLoaded(weather: Weather) {
        view?.updateLoadingState(LoadingState.SHOW_RESULTS)
        view?.updateView(weather)
    }
}