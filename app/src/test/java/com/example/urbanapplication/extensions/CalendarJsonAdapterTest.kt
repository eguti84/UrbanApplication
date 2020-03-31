package com.example.urbanapplication.extensions

import com.example.urbanapplication.repository.util.CalendarJsonAdapter
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import java.util.*

class CalendarJsonAdapterTest {

    private val dateString = "2006-06-17T00:00:00.000Z"
    private val dateCalendar: Calendar? by lazy {
        dateString.toCalendar()
    }

    @Before
    fun setUp() {

    }

    @Test
    fun `given String, when fromJson called, then return Calendar`() {
        // Given
        val dateString = "2020-06-17T00:00:00.000Z"

        // When
        val cal = CalendarJsonAdapter.fromJson(dateString)

        // Then
        assertNotNull(cal)
        assertEquals(2020, cal?.get(Calendar.YEAR))
    }

    @Test
    fun `given Calendar, when toServerString called, then return String`() {
        // Given
        val cal = "2020-06-17T00:00:00.000Z".toCalendar()

        // When
        val json = cal?.let { CalendarJsonAdapter.toServerString(it) }

        // Then
        assertFalse(json.isNullOrEmpty())
    }
}
