package com.sudhindra.schack25.data.repository

import com.sudhindra.schack25.data.api.RetrofitClient
import com.sudhindra.schack25.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
            Result.success(response.data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Mock posts based on the provided API response
     */
    suspend fun getMockPosts(): List<Post> {
        delay(800)
        return listOf(
            Post(
                id = "6c76786a-6e79-4669-aa56-80e3118bf475",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408934/devotional_content/devotional_lord_vishnu_punjabi_20251106_060207_monaww.png",
                text = "ਓਮ ਵਿਸ਼ਨੂੰ ਨਮਹ।",
                language = "Punjabi",
                createdDate = "2025-11-06T06:02:17.858741"
            ),
            Post(
                id = "f6116c8c-e614-4f81-990d-01eabff0b095",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408908/devotional_content/devotional_lord_vishnu_punjabi_20251106_060142_oxlfqa.png",
                text = "ਜੈ ਸ਼੍ਰੀ ਵਿਸ਼ਨੂੰ",
                language = "Punjabi",
                createdDate = "2025-11-06T06:01:49.346623"
            ),
            Post(
                id = "1",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408934/devotional_content/devotional_lord_vishnu_punjabi_20251106_060207_monaww.png",
                text = "ਓਮ ਵਿਸ਼ਨੂੰ ਨਮਹ।",
                language = "Punjabi",
                createdDate = "2025-11-06T06:02:17.858741"
            ),
            Post(
                id = "2",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408908/devotional_content/devotional_lord_vishnu_punjabi_20251106_060142_oxlfqa.png",
                text = "ਜੈ ਸ਼੍ਰੀ ਵਿਸ਼ਨੂੰ",
                language = "Punjabi",
                createdDate = "2025-11-06T06:01:49.346623"
            ),
            Post(
                id = "3",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408934/devotional_content/devotional_lord_vishnu_punjabi_20251106_060207_monaww.png",
                text = "ਓਮ ਵਿਸ਼ਨੂੰ ਨਮਹ।",
                language = "Punjabi",
                createdDate = "2025-11-06T06:02:17.858741"
            ),
            Post(
                id = "4",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408908/devotional_content/devotional_lord_vishnu_punjabi_20251106_060142_oxlfqa.png",
                text = "ਜੈ ਸ਼੍ਰੀ ਵਿਸ਼ਨੂੰ",
                language = "Punjabi",
                createdDate = "2025-11-06T06:01:49.346623"
            ),
            Post(
                id = "5",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408934/devotional_content/devotional_lord_vishnu_punjabi_20251106_060207_monaww.png",
                text = "ਓਮ ਵਿਸ਼ਨੂੰ ਨਮਹ।",
                language = "Punjabi",
                createdDate = "2025-11-06T06:02:17.858741"
            ),
            Post(
                id = "6",
                imageUrl = "https://res.cloudinary.com/dntocknoj/image/upload/v1762408908/devotional_content/devotional_lord_vishnu_punjabi_20251106_060142_oxlfqa.png",
                text = "ਜੈ ਸ਼੍ਰੀ ਵਿਸ਼ਨੂੰ",
                language = "Punjabi",
                createdDate = "2025-11-06T06:01:49.346623"
            ),
        )
    }
}

