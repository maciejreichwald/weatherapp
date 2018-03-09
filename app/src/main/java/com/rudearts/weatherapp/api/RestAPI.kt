package com.rudearts.weatherapp.api

import com.rudearts.weatherapp.model.external.WeatherResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RestAPI {

    companion object {
        private const val APPID = "51a212e5ece3578a09bb0074db740507"
    }

    @GET("/weather?units=metric&APPID=$APPID")
    fun getWeather(@Query("id") id:Long): Single<Response<WeatherResponse>>

}
