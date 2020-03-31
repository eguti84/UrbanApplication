package com.example.urbanapplication.repository.remote

import com.example.urbanapplication.model.UrbanResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanService {
    @Headers(HEADER_RAPID_API_KEY)
    @GET("/define")

    suspend fun getDefinitions(@Query("term") term: String): UrbanResponse

    companion object {
        private const val RAPID_API_KEY = "178a49fa98msh8c40e2c7437d459p143e7cjsn6f192c25f3d8"
        const val HEADER_RAPID_API_KEY = "X-RapidAPI-Key: $RAPID_API_KEY"
    }
}
