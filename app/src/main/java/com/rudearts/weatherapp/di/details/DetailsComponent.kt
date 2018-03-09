package com.rudearts.weatherapp.di.details

import com.rudearts.weatherapp.di.ActivityScope
import com.rudearts.weatherapp.di.app.AppComponent
import com.rudearts.weatherapp.ui.details.DetailsActivity
import com.rudearts.weatherapp.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(DetailsModule::class)])
interface DetailsComponent {
    fun inject(activity: DetailsActivity)
}