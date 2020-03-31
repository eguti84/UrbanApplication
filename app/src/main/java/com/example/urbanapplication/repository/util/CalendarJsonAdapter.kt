package com.example.urbanapplication.repository.util

import com.example.urbanapplication.extensions.toCalendar
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

object CalendarJsonAdapter {
    @FromJson
    fun fromJson(date: String?): Calendar? = date?.toCalendar()

    @ToJson
    fun toServerString(calendar: Calendar): String? =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).format(calendar.time)
}