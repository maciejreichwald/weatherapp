package com.rudearts.weatherapp.di.app

import android.content.Context
import com.rudearts.soundapp.util.loader.AssetLoader
import com.rudearts.weatherapp.dbextractor.database.DatabaseController
import com.rudearts.weatherapp.dbextractor.util.AssetMapper
import com.rudearts.weatherapp.dbextractor.util.DbGenerator
import com.rudearts.weatherapp.model.greendao.DaoSession
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule (private val context:Context) {

    @Provides
    @Singleton
    fun providesDatabase() = DatabaseController(context).session

    @Provides
    fun providesLoader() = AssetLoader(context)

    @Provides
    fun providesMapper() = AssetMapper()

    @Provides
    fun providesDbGenerator(database: DaoSession,
                            loader:AssetLoader,
                            mapper: AssetMapper) = DbGenerator(database, loader, mapper)


}