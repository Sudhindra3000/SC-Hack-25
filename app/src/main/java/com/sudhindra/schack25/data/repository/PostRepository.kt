package com.sudhindra.schack25.data.repository

import com.sudhindra.schack25.data.api.RetrofitClient
import com.sudhindra.schack25.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for managing post data
 */
class PostRepository {
    
    private val apiService = RetrofitClient.apiService
    
    /**
     * Fetch posts from the API
     */
    suspend fun getPosts(): Result<List<Post>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getPosts()
            Result.success(response.posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

