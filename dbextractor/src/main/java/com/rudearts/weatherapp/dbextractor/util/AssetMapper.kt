package com.rudearts.weatherapp.dbextractor.util

import com.rudearts.weatherapp.model.asset.CityAsset
import com.rudearts.weatherapp.model.asset.CityPositionAsset
import com.rudearts.weatherapp.model.greendao.CityDb

class AssetMapper {

    fun city2db(city:CityAsset) = with(city) {
        val position = position2db(coord)
        return@with CityDb(long2nonnull(id), string2nonnull(name), string2nonnull(country), position.first, position.second)
    }

    private fun string2nonnull(name: String?) = name ?: ""

    private fun long2nonnull(id: Long?) = id ?: 0L

    internal fun position2db(coord: CityPositionAsset?) = Pair(
            coord?.lat ?: 0.0,
            coord?.lon ?: 0.0)

}