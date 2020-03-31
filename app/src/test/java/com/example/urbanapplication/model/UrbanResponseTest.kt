package com.example.urbanapplication.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class UrbanResponseTest {

    @Test
    fun `given params, when constructor called, then return UrbanResponse`() {
        val obj = UrbanResponse(list = listOf())

        // Then
        assertEquals(listOf<Definition>(), obj.list)
    }
}