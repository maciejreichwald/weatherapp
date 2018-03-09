package com.rudearts.weatherapp.di.app

import com.rudearts.weatherapp.domain.LoadWeatherUseCase
import com.rudearts.weatherapp.domain.SearchCitiesUseCase
import com.rudearts.weatherapp.util.ExternalMapper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, ExternalModule::class])
interface AppComponent {
    val searchCities: SearchCitiesUseCase
    val loadWeather:LoadWeatherUseCase
}