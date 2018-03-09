package com.rudearts.weatherapp.ui

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.rudearts.weatherapp.R
import com.rudearts.extentions.bind


abstract class ToolbarActivity : AppCompatActivity() {

    private val toolbar:Toolbar by bind(R.id.toolbar)
    private val toolbarTitle:TextView by bind(R.id.toolbar_title)
    protected val coordinatorLayout:CoordinatorLayout by bind(R.id.coordinator_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)
        includeSubview()
        setupActionBar()
    }

    override fun setTitle(title: CharSequence) {
        super.setTitle("")
        toolbarTitle.text = title
    }

    private fun includeSubview() {
        View.inflate(this, provideSubContentViewId(), coordinatorLayout)
    }

    protected fun showSnackMessage(message: String) = coordinatorLayout.let {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.primary))
    }

    abstract fun provideSubContentViewId(): Int
}