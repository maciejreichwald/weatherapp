package com.rudearts.weatherapp.domain

import com.rudearts.weatherapp.api.RestAPI
import com.rudearts.weatherapp.model.external.WeatherResponse
import com.rudearts.weatherapp.model.greendao.DaoSession
import com.rudearts.weatherapp.model.greendao.WeatherDbDao
import com.rudearts.weatherapp.model.local.City
import com.rudearts.weatherapp.model.local.Weather
import com.rudearts.weatherapp.util.ExternalMapper
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class LoadWeatherUseCase @Inject constructor(
        val database:DaoSession,
        val restApi: RestAPI,
        val mapper:ExternalMapper) {

    fun loadWeather(cityId:Long):Single<Weather> = restApi.getWeather(cityId)
                .map { response -> handleResponse(response) }
                .onErrorReturn { loadFromDb(cityId) }

    internal fun loadFromDb(cityId:Long):Weather {
        val queryBuilder = database.weatherDbDao.queryBuilder()

        val weathers = queryBuilder.where(WeatherDbDao.Properties.Id.eq(cityId)).list()
        val weather = weathers.firstOrNull()

        return mapper.weather2local(weather)
    }

    internal fun handleResponse(response: Response<WeatherResponse>?):Weather {
        val weather = mapper.weatherResponse2local(response?.body())

        if (weather.id != ExternalMapper.DEFAULT_ID) saveWeather(weather)

        return weather
    }

    internal fun saveWeather(weather: Weather) {
        val weatherDb = mapper.weather2db(weather)
        database.weatherDbDao.insertOrReplace(weatherDb)
    }

}