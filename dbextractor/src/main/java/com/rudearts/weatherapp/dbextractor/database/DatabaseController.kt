package com.rudearts.weatherapp.dbextractor.database

import android.content.Context
import com.rudearts.weatherapp.model.greendao.DaoMaster
import com.rudearts.weatherapp.model.greendao.DaoSession

class DatabaseController constructor(context: Context){

    companion object {
        val DATABASE_NAME = "weather.db"
    }

    var session:DaoSession

    init {
        val helper = DaoMaster.DevOpenHelper(context, DATABASE_NAME)
        session = DaoMaster(helper.writableDatabase).newSession()
    }
}