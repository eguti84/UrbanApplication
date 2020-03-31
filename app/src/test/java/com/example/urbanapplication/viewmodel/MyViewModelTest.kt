package com.example.urbanapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.urbanapplication.BaseTest
import com.example.urbanapplication.model.Definition
import com.example.urbanapplication.model.UrbanResponse
import com.example.urbanapplication.repository.remote.UrbanService
import com.example.urbanapplication.testuitils.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MyViewModelTest : BaseTest() {
    @get:Rule
    var instantTestExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var testCoroutineRule =
        TestCoroutineRule()
    private lateinit var viewmodel: MyViewModel

    private val urbanService = mockk<UrbanService> {
        coEvery {
            getDefinitions(any())
        } returns UrbanResponse(listOf())
    }

    override fun setUp() {
        super.setUp()
        viewmodel = MyViewModel(urbanService)
    }

    @Test
    fun `given VALID query, when fetchDefinition called, then update urbanResponse`() {
        // Given
        val mockObserver = createObserver<UrbanResponse>()
        viewmodel.urbanResponse.observeForever(mockObserver)


        // When
        viewmodel.fetchDefinition("query")

        // Then
        val captureList = mutableListOf<UrbanResponse>()
        verify { mockObserver.onChanged(capture(captureList)) }

        val response = captureList[0]
        assertEquals(listOf<Definition>(), response.list)
    }
}