package com.rudearts.weatherapp.model

import com.rudearts.weatherapp.R

class WeatherCodes {

    private val descriptionCodes = hashMapOf<Int,Int>(
            200 to R.string.thunderstorm_0,
            201 to R.string.thunderstorm_1,
            202 to R.string.thunderstorm_2,
            210 to R.string.thunderstorm_3,
            211 to R.string.thunderstorm_4,
            212 to R.string.thunderstorm_5,
            221 to R.string.thunderstorm_6,
            230 to R.string.thunderstorm_7,
            231 to R.string.thunderstorm_8,
            232 to R.string.thunderstorm_9,

            300 to R.string.drizzle_0,
            301 to R.string.drizzle_1,
            302 to R.string.drizzle_2,
            310 to R.string.drizzle_3,
            311 to R.string.drizzle_4,
            312 to R.string.drizzle_5,
            313 to R.string.drizzle_6,
            314 to R.string.drizzle_7,
            321 to R.string.drizzle_8,

            500 to R.string.rain_0,
            501 to R.string.rain_1,
            502 to R.string.rain_2,
            503 to R.string.rain_3,
            504 to R.string.rain_4,
            511 to R.string.rain_5,
            520 to R.string.rain_6,
            521 to R.string.rain_7,
            522 to R.string.rain_8,
            531 to R.string.rain_9,

            600 to R.string.snow_0,
            601 to R.string.snow_1,
            602 to R.string.snow_2,
            611 to R.string.snow_3,
            612 to R.string.snow_4,
            615 to R.string.snow_5,
            616 to R.string.snow_6,
            620 to R.string.snow_7,
            621 to R.string.snow_8,
            622 to R.string.snow_9,

            701 to R.string.atmosphere_0,
            711 to R.string.atmosphere_1,
            721 to R.string.atmosphere_2,
            731 to R.string.atmosphere_3,
            741 to R.string.atmosphere_4,
            751 to R.string.atmosphere_5,
            761 to R.string.atmosphere_6,
            762 to R.string.atmosphere_7,
            771 to R.string.atmosphere_8,
            781 to R.string.atmosphere_9,

            800 to R.string.clouds_0,
            801 to R.string.clouds_1,
            802 to R.string.clouds_2,
            803 to R.string.clouds_3,
            804 to R.string.clouds_4,

            900 to R.string.extreme_0,
            901 to R.string.extreme_1,
            902 to R.string.extreme_2,
            903 to R.string.extreme_3,
            904 to R.string.extreme_4,
            905 to R.string.extreme_5,
            906 to R.string.extreme_6,

            951 to R.string.additional_0,
            952 to R.string.additional_1,
            953 to R.string.additional_2,
            954 to R.string.additional_3,
            955 to R.string.additional_4,
            956 to R.string.additional_5,
            957 to R.string.additional_6,
            958 to R.string.additional_7,
            959 to R.string.additional_8,
            960 to R.string.additional_9,
            961 to R.string.additional_A,
            962 to R.string.additional_B
    )

    private val iconCodes = hashMapOf<String,Int>(
            "01d" to R.drawable.d01,
            "02d" to R.drawable.d02,
            "03d" to R.drawable.d03,
            "04d" to R.drawable.d04,
            "09d" to R.drawable.d09,
            "10d" to R.drawable.d10,
            "11d" to R.drawable.d11,
            "13d" to R.drawable.d13,
            "50d" to R.drawable.d50,
            "01n" to R.drawable.n01,
            "02n" to R.drawable.n02,
            "03n" to R.drawable.n03,
            "04n" to R.drawable.n04,
            "09n" to R.drawable.n09,
            "10n" to R.drawable.n10,
            "11n" to R.drawable.n11,
            "13n" to R.drawable.n13,
            "50n" to R.drawable.n50
    )

    fun hasIconCode(code:String?) = iconCodes.containsKey(code)

    fun iconIdByCode(code:String?) = code.let { iconCodes[code] } ?: 0

    fun hasDescriptionCode(code:Int?) = descriptionCodes.containsKey(code)

    fun descriptionIdByCode(code:Int?) = code.let { descriptionCodes[code] } ?: 0

}