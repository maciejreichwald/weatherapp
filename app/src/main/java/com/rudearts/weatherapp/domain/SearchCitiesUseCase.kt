package com.rudearts.weatherapp.domain

import android.util.Log
import com.rudearts.weatherapp.model.greendao.CityDbDao
import com.rudearts.weatherapp.model.greendao.DaoSession
import com.rudearts.weatherapp.model.local.City
import com.rudearts.weatherapp.util.ExternalMapper
import io.reactivex.Single
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor(
        val database:DaoSession,
        val mapper:ExternalMapper) {

    companion object {
        private const val QUERY_LIMIT = 10000
    }

    fun search(city:String):Single<List<City>> =
            Single.fromCallable { database.cityDbDao.queryBuilder() }
                    .map { queryBuilder ->
                        queryBuilder.where(CityDbDao.Properties.Name.like("$city%"))
                                .orderAsc(CityDbDao.Properties.Name)
                                .limit(QUERY_LIMIT)
                                .list()
                    }
                    .map { cities ->
                        cities.map { city -> mapper.city2local(city)} }

}