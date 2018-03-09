package com.rudearts.weatherapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestController {

    companion object {
        val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }

    /**
     * It is lateinit only because compilator is not allowing it...
     */
    internal lateinit var restApi: RestAPI

    init {
        setup()
    }

    internal fun setup() {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        restApi = retrofit.create(RestAPI::class.java)
    }
}
