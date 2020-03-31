package com.example.urbanapplication.extensions

import junit.framework.TestCase.*
import org.junit.Test
import java.util.*

class StringExtTest {

    @Test
    fun `given String, when toCalendar called, then return Calendar`() {
        // Given
        val dateString = "2020-06-17T00:00:00.000Z"

        // When
        val cal = dateString.toCalendar()

        // Then
        assertNotNull(cal)
        assertEquals(2020, cal?.get(Calendar.YEAR))
    }

    @Test
    fun `given INVALID String, when fromJson called, then return null`() {
        // Given
        val dateString = "202"

        // When
        val cal = dateString.toCalendar()

        // Then
        assertNull(cal)
    }
}