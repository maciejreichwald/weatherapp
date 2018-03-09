package com.rudearts.weatherapp

import android.app.Application
import com.rudearts.weatherapp.di.app.AppComponent
import com.rudearts.weatherapp.di.app.DaggerAppComponent
import com.rudearts.weatherapp.di.app.DomainModule
import com.rudearts.weatherapp.di.app.ExternalModule

class WeatherApplication : Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        createComponent()
    }

    internal fun createComponent() {
        appComponent =  DaggerAppComponent.builder()
                .domainModule(DomainModule())
                .externalModule(ExternalModule(this))
                .build()
    }
}