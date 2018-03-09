package com.rudearts.weatherapp.di.app

import com.rudearts.weatherapp.api.RestAPI
import com.rudearts.weatherapp.domain.LoadWeatherUseCase
import com.rudearts.weatherapp.domain.SearchCitiesUseCase
import com.rudearts.weatherapp.model.greendao.DaoSession
import com.rudearts.weatherapp.util.ExternalMapper
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesSearchCityUseCase(
            database:DaoSession,
            mapper:ExternalMapper) = SearchCitiesUseCase(database, mapper)

    @Provides
    fun providesLoadWeatherUseCase(
            database:DaoSession,
            restAPI: RestAPI,
            mapper:ExternalMapper) = LoadWeatherUseCase(database, restAPI, mapper)
}