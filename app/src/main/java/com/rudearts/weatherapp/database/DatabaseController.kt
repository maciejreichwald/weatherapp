package com.rudearts.weatherapp.database

import android.content.Context
import com.rudearts.weatherapp.model.greendao.DaoMaster
import com.rudearts.weatherapp.model.greendao.DaoSession

class DatabaseController constructor(context: Context){

    companion object {
        private val DATABASE_NAME = "weather.db"
    }

    var session:DaoSession

    init {
        val helper = DatabaseRelocator(context, DATABASE_NAME, null)
        session = DaoMaster(helper.writableDatabase).newSession()
    }
}