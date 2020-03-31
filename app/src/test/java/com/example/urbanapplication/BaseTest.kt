package com.example.urbanapplication

import androidx.lifecycle.Observer
import io.mockk.MockKAnnotations
import io.mockk.spyk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

abstract class BaseTest {

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    open fun tearDown() {
        unmockkAll()
    }

    fun <T> createObserver(): Observer<T> = spyk(Observer { })
}