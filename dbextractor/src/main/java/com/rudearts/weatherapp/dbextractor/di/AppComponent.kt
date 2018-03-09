package com.rudearts.weatherapp.di.app

import com.rudearts.weatherapp.dbextractor.ui.MainActivity
import com.rudearts.weatherapp.dbextractor.util.DbGenerator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class])
interface AppComponent {
    val dbGenerator : DbGenerator

    fun inject(activity: MainActivity)
}