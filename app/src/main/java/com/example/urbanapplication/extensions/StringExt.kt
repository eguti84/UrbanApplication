package com.example.urbanapplication.extensions

import java.text.SimpleDateFormat
import java.util.*

const val DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
fun String.toCalendar(inputPattern: String = DEFAULT_PATTERN): Calendar? {
    val sdf = SimpleDateFormat(inputPattern, Locale.getDefault())
    val date = try {
        sdf.parse(this)
    } catch (e: Exception) {
        null
    }
    return when (date) {
        null -> null
        else -> Calendar.getInstance().apply { time = date }
    }
}