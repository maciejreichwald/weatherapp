package com.rudearts.weatherapp.di.main

import android.content.Context
import com.rudearts.weatherapp.di.ActivityScope
import com.rudearts.weatherapp.domain.SearchCitiesUseCase
import com.rudearts.weatherapp.ui.main.MainContract
import com.rudearts.weatherapp.ui.main.MainPresenter
import com.rudearts.weatherapp.ui.main.CityAdapter
import dagger.Module
import dagger.Provides


@Module
class MainModule(private val context:Context, private val view:MainContract.View) {

    @Provides
    @ActivityScope
    fun providesMainView() = view

    @Provides
    @ActivityScope
    fun providesContext() = context

    @Provides
    @ActivityScope
    fun providesMainPresenter(
            view:MainContract.View,
            searchCities: SearchCitiesUseCase):MainContract.Presenter = MainPresenter(view, searchCities)

    @Provides
    @ActivityScope
    fun providesSiteAdapter() = CityAdapter(context)
}