package com.rudearts.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        val TACK_DATE_FORMAT = "yyyy-MM-DD'T'hh:mm:ss'Z'"
        val YEAR_FORMAT = "yyyy"
    }

    internal var trackFormat = SimpleDateFormat(TACK_DATE_FORMAT)
    internal var yearFormat = SimpleDateFormat(YEAR_FORMAT)

    fun string2date(textDate:String?) = textDate?.let { trackFormat.parse(it) } ?: Date(0)

    fun long2date(time:Long?) = Date(time ?: 0L)

    fun year2date(year:Long?):Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year?.toInt() ?: 0)
        return calendar.time
    }

    fun date2year(date:Date) = yearFormat.format(date)
}
