package com.rudearts.weatherapp.di.app

import android.content.Context
import com.rudearts.weatherapp.api.RestAPI
import com.rudearts.weatherapp.api.RestController
import com.rudearts.weatherapp.database.DatabaseController
import com.rudearts.weatherapp.model.WeatherCodes
import com.rudearts.weatherapp.model.greendao.DaoSession
import com.rudearts.weatherapp.util.ExternalMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExternalModule(private val context:Context) {

    @Provides
    @Singleton
    fun provideDatabase():DaoSession  = DatabaseController(context).session

    @Provides
    @Singleton
    fun provideRestApi():RestAPI = RestController().restApi

    @Provides
    fun provideWeatherCodes() = WeatherCodes()

    @Provides
    fun provideExternalMapper(weatherCodes: WeatherCodes) = ExternalMapper(weatherCodes)

}