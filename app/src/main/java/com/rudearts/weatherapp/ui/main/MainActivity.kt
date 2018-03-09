package com.rudearts.weatherapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.rudearts.extentions.bind
import com.rudearts.extentions.visible
import com.rudearts.weatherapp.R
import com.rudearts.weatherapp.WeatherApplication
import com.rudearts.weatherapp.di.main.DaggerMainComponent
import com.rudearts.weatherapp.di.main.MainModule
import com.rudearts.weatherapp.model.LoadingState
import com.rudearts.weatherapp.model.local.City
import com.rudearts.weatherapp.ui.ToolbarActivity
import com.rudearts.weatherapp.ui.details.DetailsActivity
import javax.inject.Inject

/**
 * Yes, it is open, you can see in MainActivityTest bottom comment why
 */
open class MainActivity : ToolbarActivity(), MainContract.View {

    @Inject internal lateinit var presenter: MainContract.Presenter
    @Inject internal lateinit var adapter: CityAdapter

    internal val inputCity: EditText by bind(R.id.city_input)
    internal val progressBar: View by bind(R.id.progress_bar)
    internal val emptyView: View by bind(R.id.empty_view)
    internal val btnSearch: View by bind(R.id.search_btn)
    internal val listCities:RecyclerView by bind(R.id.city_recycler)

    override fun provideSubContentViewId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    internal fun setup() {
        inject()
        setupTitle()
        setupViews()

        updateLoadingState(LoadingState.NO_RESULTS)
    }

    internal fun inject() {
        createComponent().apply {
            this.inject(this@MainActivity)
        }
    }

    internal fun createComponent() = DaggerMainComponent.builder()
            .appComponent(WeatherApplication.appComponent)
            .mainModule(MainModule(this, this))
            .build()

    internal fun setupTitle() {
        title = getString(R.string.app_name)
    }

    internal fun setupViews() {
        setupInput()
        setupBtn()
        setupList()
    }

    private fun setupList() {
        adapter.setOnClickListener { city -> onItemClicked(city) }
        listCities.adapter = adapter
    }

    private fun onItemClicked(city: City) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.CITY_ID, city.id)
            putExtra(DetailsActivity.CITY_NAME, city.name)
        }
        startActivity(intent)
    }

    internal fun setupBtn() {
        btnSearch.setOnClickListener { onBtnLoadClick() }
    }

    internal fun onBtnLoadClick() {
        presenter.search(inputCity.text.toString())
        hideKeyboard()
    }

    internal fun setupInput() {
        inputCity.setOnEditorActionListener { _, actionId, _ -> onEditorAction(actionId) }
    }

    internal fun onEditorAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onBtnLoadClick()
        }
        return false
    }

    override fun updateLoadingState(state:LoadingState) {
        progressBar.visible = state == LoadingState.LOADING
        listCities.visible = state == LoadingState.SHOW_RESULTS
        emptyView.visible = state == LoadingState.NO_RESULTS
    }

    override fun updateCities(cities: List<City>) {
        adapter.updateCities(cities)
    }

    override fun showMessage(message: String) {
        showSnackMessage(message)
    }

    override fun hideKeyboard() {
        currentFocus?.let {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

}
