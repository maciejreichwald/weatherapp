package com.rudearts.weatherapp.di.details

import android.content.Context
import com.rudearts.weatherapp.di.ActivityScope
import com.rudearts.weatherapp.domain.LoadWeatherUseCase
import com.rudearts.weatherapp.domain.SearchCitiesUseCase
import com.rudearts.weatherapp.ui.details.DetailsContract
import com.rudearts.weatherapp.ui.details.DetailsPresenter
import com.rudearts.weatherapp.ui.main.MainContract
import com.rudearts.weatherapp.ui.main.MainPresenter
import com.rudearts.weatherapp.ui.main.CityAdapter
import dagger.Module
import dagger.Provides


@Module
class DetailsModule(private val context:Context, private val view:DetailsContract.View) {

    @Provides
    @ActivityScope
    fun providesMainView() = view

    @Provides
    @ActivityScope
    fun providesContext() = context

    @Provides
    @ActivityScope
    fun providesDetailsPresenter(
            view:DetailsContract.View,
            loadWeather:LoadWeatherUseCase):DetailsContract.Presenter = DetailsPresenter(view, loadWeather)
}