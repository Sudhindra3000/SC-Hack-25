package com.sudhindra.schack25.data.api

import com.sudhindra.schack25.data.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API service interface for Retrofit
 * TODO: Update the base URL and endpoints according to your actual API
 */
interface ApiService {
    
    /**
     * Fetch posts from the API
     * TODO: Update endpoint path and parameters as needed
     */
    @GET("history")
    suspend fun getPosts(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): PostResponse
    
    // Add more endpoints as needed
}

