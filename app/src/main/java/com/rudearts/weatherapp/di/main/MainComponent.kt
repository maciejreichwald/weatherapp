package com.rudearts.weatherapp.di.main

import com.rudearts.weatherapp.di.ActivityScope
import com.rudearts.weatherapp.di.app.AppComponent
import com.rudearts.weatherapp.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(MainModule::class)])
interface MainComponent {
    fun inject(activity: MainActivity)
}