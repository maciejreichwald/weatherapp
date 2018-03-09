package com.rudearts.weatherapp.ui.main

import com.rudearts.extentions.threadToAndroid
import com.rudearts.weatherapp.domain.SearchCitiesUseCase
import com.rudearts.weatherapp.model.LoadingState
import com.rudearts.weatherapp.model.local.City
import javax.inject.Inject

class MainPresenter @Inject constructor(
        internal val view:MainContract.View?,
        internal val searchUseCase:SearchCitiesUseCase) : MainContract.Presenter {

    override fun search(city: String) {
        view?.updateLoadingState(LoadingState.LOADING)

        searchUseCase.search(city)
                .threadToAndroid()
                .subscribe(
                        {cities -> onCitiesFound(cities)},
                        {error -> onError(error)}
                )
    }

    private fun onError(error: Throwable) {
        view?.updateLoadingState(LoadingState.NO_RESULTS)

        view?.showMessage(error.toString())
    }

    private fun onCitiesFound(cities: List<City>) {
        view?.updateCities(cities)

        when(cities.isEmpty()) {
            true -> view?.updateLoadingState(LoadingState.NO_RESULTS)
            else -> view?.updateLoadingState(LoadingState.SHOW_RESULTS)
        }
    }
}