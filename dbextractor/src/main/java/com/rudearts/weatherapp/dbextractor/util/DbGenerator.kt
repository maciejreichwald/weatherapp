package com.rudearts.weatherapp.dbextractor.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.mindorks.nybus.NYBus
import com.rudearts.soundapp.util.loader.AssetLoader
import com.rudearts.weatherapp.dbextractor.model.ProgressEvent
import com.rudearts.weatherapp.model.asset.CityAsset
import com.rudearts.weatherapp.model.greendao.DaoSession
import io.reactivex.Single
import javax.inject.Inject


class DbGenerator @Inject constructor(
        val database:DaoSession,
        val loader: AssetLoader,
        val mapper:AssetMapper) {

    companion object {
        private val ASSET_PATH = "citylist.json"
    }

    var idCounter = 0

    fun generateDb():Single<Unit> =
            Single.fromCallable { loader.loadAssetByObject(ASSET_PATH, this::saveCityAsset) }

    private fun saveCityAsset(jsonReader: JsonReader) {
        val cityAsset = Gson().fromJson<CityAsset>(jsonReader, CityAsset::class.java)

        val dao = database.cityDbDao
        dao.insertOrReplace(mapper.city2db(cityAsset))

        updateProgress(cityAsset)
    }

    private fun updateProgress(cityAsset:CityAsset) {
        idCounter++

        if(idCounter % 1000 != 1) return

        Log.d("City", "City: ${cityAsset.name} ID: ${cityAsset.id} Country: ${cityAsset.country} Counter: $idCounter")
        NYBus.get().post(ProgressEvent(idCounter))
    }

}