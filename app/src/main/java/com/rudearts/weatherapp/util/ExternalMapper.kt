package com.rudearts.weatherapp.util

import com.rudearts.weatherapp.R
import com.rudearts.weatherapp.model.WeatherCodes
import com.rudearts.weatherapp.model.external.MainExternal
import com.rudearts.weatherapp.model.external.WeatherExternal
import com.rudearts.weatherapp.model.external.WeatherResponse
import com.rudearts.weatherapp.model.greendao.CityDb
import com.rudearts.weatherapp.model.greendao.WeatherDb
import com.rudearts.weatherapp.model.local.City
import com.rudearts.weatherapp.model.local.Weather
import javax.inject.Inject

class ExternalMapper @Inject constructor(val weatherCodes: WeatherCodes) {

    companion object {
        private const val UNKNOWN_ID = R.string.unknown_small
        val DEFAULT_ID = 0L
    }

    fun city2local(city: CityDb) = with(city) {
        City(id, name, country, latitude, longitude)
    }

    fun weatherResponse2local(response:WeatherResponse?) = with(response ?: WeatherResponse()) {
        val (temperature, humidity, pressure) = main2local(main)
        return@with Weather(
                id2nonnull(id),
                int2nonnull(cod),
                cod2main(cod),
                cod2description(cod),
                weathers2icon(weather),
                temperature,
                pressure,
                humidity)
    }

    fun weather2local(weather:WeatherDb?) = with(weather ?: WeatherDb()) {
        Weather(id2nonnull(id), cod, main, description, icon, temperature, pressure, humidity)
    }

    fun weather2db(weather:Weather) = with(weather) {
        WeatherDb(id, cod, main, description, icon, temperature, pressure, humidity)
    }

    private fun weathers2icon(weathers: List<WeatherExternal>?): Int {
        val weather = weathers?.firstOrNull()
        return string2iconId(weather?.icon)
    }

    private fun string2iconId(iconCode: String?) = when(weatherCodes.hasIconCode(iconCode)) {
        true -> weatherCodes.iconIdByCode(iconCode!!)
        false -> R.drawable.d50
    }

    private fun cod2description(cod: Int?) = when(weatherCodes.hasDescriptionCode(cod)) {
        true -> weatherCodes.descriptionIdByCode(cod!!)
        false -> UNKNOWN_ID
    }

    private fun cod2main(cod: Int?) = when(cod) {
        in 200..232 -> R.string.thunderstorm
        in 300..321 -> R.string.drizzle
        in 500..531 -> R.string.rain
        in 600..622 -> R.string.snow
        in 701..781 -> R.string.atmosphere
        in 800..800 -> R.string.clear_sky
        in 801..804 -> R.string.clouds
        in 900..906 -> R.string.extreme
        in 951..962 -> R.string.additional
        else -> UNKNOWN_ID
    }

    private fun main2local(main: MainExternal?): Triple<Double, Double, Double> {
        val temperature = main?.temp ?: 0.0
        val humidity = main?.humidity ?: 0.0
        val pressure = main?.pressure ?: 0.0
        return Triple(temperature, humidity, pressure)
    }

    private fun id2nonnull(id: Long?) = id ?: DEFAULT_ID

    private fun int2nonnull(param: Int?) = param ?: 0
}