package com.rudearts.weatherapp.dbextractor.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ProgressBar
import com.mindorks.nybus.NYBus
import com.mindorks.nybus.annotation.Subscribe
import com.mindorks.nybus.thread.NYThread
import com.rudearts.extentions.bind
import com.rudearts.extentions.threadToAndroid
import com.rudearts.extentions.visible
import com.rudearts.weatherapp.dbextractor.DbExtractorApplication
import com.rudearts.weatherapp.dbextractor.R
import com.rudearts.weatherapp.dbextractor.model.ProgressEvent
import com.rudearts.weatherapp.dbextractor.util.DbGenerator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject internal lateinit var dbGenerator: DbGenerator

    internal val progressBar:ProgressBar by bind(R.id.progress_bar)
    internal val container:View by bind(R.id.container)
    internal val label:View by bind(R.id.label)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        inject()
        setupProgress()
        generateDb()
    }

    private fun setupProgress() {
        NYBus.get().register(this)

        progressBar.max = 210000
        progressBar.progress = 0
    }

    private fun generateDb() {
        dbGenerator.generateDb()
                .threadToAndroid()
                .subscribe(
                        {onComplete()},
                        {error -> onError(error) }
                )
    }

    private fun onComplete() {
        finishLoading()
        Snackbar.make(container, R.string.completed, Snackbar.LENGTH_LONG)
    }

    private fun finishLoading() {
        progressBar.visible = false
        label.visible = false
    }

    private fun onError(error:Throwable) {
        finishLoading()
        Snackbar.make(container, error.toString(), Snackbar.LENGTH_LONG)
    }

    private fun inject() {
        DbExtractorApplication.appComponent.inject(this)
    }

    @Subscribe(threadType = NYThread.MAIN)
    fun onEvent(event:ProgressEvent) {
        progressBar.progress = event.progress
    }
}
