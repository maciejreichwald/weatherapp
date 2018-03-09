package com.rudearts.weatherapp.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.rudearts.extentions.bind
import com.rudearts.extentions.visible
import com.rudearts.weatherapp.R
import com.rudearts.weatherapp.WeatherApplication
import com.rudearts.weatherapp.di.details.DaggerDetailsComponent
import com.rudearts.weatherapp.di.details.DetailsModule
import com.rudearts.weatherapp.model.LoadingState
import com.rudearts.weatherapp.model.local.Weather
import com.rudearts.weatherapp.ui.ToolbarActivity
import javax.inject.Inject

class DetailsActivity : ToolbarActivity(), DetailsContract.View {

    companion object {
        val CITY_ID = "CityId"
        val CITY_NAME = "CityName"
    }

    @Inject internal lateinit var presenter: DetailsContract.Presenter

    internal val progressBar: View by bind(R.id.progress_bar)
    internal val container: View by bind(R.id.container)
    internal val imgIcon: ImageView by bind(R.id.icon_img)
    internal val lblMain:TextView by bind(R.id.main_lbl)
    internal val lblDescription:TextView by bind(R.id.description_lbl)
    internal val lblTemperature:TextView by bind(R.id.temperature_lbl)
    internal val lblPressure:TextView by bind(R.id.pressure_lbl)
    internal val lblHumidity:TextView by bind(R.id.humidity_lbl)

    override fun provideSubContentViewId() = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        inject()
        setupTitle()
        loadData()
    }

    private fun loadData() {
        presenter.loadWeather(intent.getLongExtra(CITY_ID, 0L))
    }

    internal fun setupTitle() {
        title = intent.getStringExtra(CITY_NAME)
    }

    internal fun inject() {
        createComponent().apply {
            this.inject(this@DetailsActivity)
        }
    }

    internal fun createComponent() = DaggerDetailsComponent.builder()
            .appComponent(WeatherApplication.appComponent)
            .detailsModule(DetailsModule(this, this))
            .build()

    override fun updateLoadingState(state: LoadingState) {
        progressBar.visible = state == LoadingState.LOADING
        container.visible = state == LoadingState.SHOW_RESULTS
        imgIcon.visible = state == LoadingState.SHOW_RESULTS
    }

    override fun updateView(weather: Weather) {
        lblMain.text = getString(weather.main)
        lblDescription.text = getString(weather.description)
        lblTemperature.text = getString(R.string.temperature_value, weather.temperature)
        lblPressure.text = getString(R.string.pressure_value, weather.pressure)
        lblHumidity.text = getString(R.string.humidity_value, weather.humidity)
        imgIcon.setImageResource(weather.icon)
    }
}
