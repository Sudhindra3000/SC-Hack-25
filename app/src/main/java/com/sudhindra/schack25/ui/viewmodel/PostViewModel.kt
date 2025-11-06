package com.sudhindra.schack25.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhindra.schack25.data.model.Post
import com.sudhindra.schack25.data.repository.PostRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * UI State for posts
 */
sealed interface PostUiState {
    object Loading : PostUiState
    data class Success(val posts: List<Post>) : PostUiState
    data class Error(val message: String) : PostUiState
}

/**
 * ViewModel for managing post data
 */
class PostViewModel(
    private val repository: PostRepository = PostRepository()
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.Loading)
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()
    
    init {
        loadPosts()
    }
    
    /**
     * Load posts from the API
     * Currently using MOCK DATA for testing
     */
    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostUiState.Loading
            
            // Simulate network delay
            delay(1500)
            
            // MOCK DATA - Replace with actual API call when ready
            val mockPosts = repository.getMockPosts()
            _uiState.value = PostUiState.Success(mockPosts)
//
//             Uncomment below to use real API
//            repository.getPosts()
//                .onSuccess { posts ->
//                    _uiState.value = PostUiState.Success(posts)
//                }
//                .onFailure { exception ->
//                    _uiState.value = PostUiState.Error(
//                        exception.message ?: "Unknown error occurred"
//                    )
//                }
        }
    }
    
    /**
     * Refresh posts
     */
    fun refresh() {
        loadPosts()
    }
    
    /**
     * Generate mock posts for testing
     */
//    private fun getMockPosts(): List<Post> {
//        return listOf(
//            Post(
//                id = "1",
//                imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4",
//                text = "The mountains are calling and I must go",
//                language = "English",
//                createdDate = "2025-11-06T02:00:00.000000",
//            ),
//            Post(
//                id = "2",
//                imageUrl = "https://images.unsplash.com/photo-1511300636408-a63a89df3482",
//                text = "Not all who wander are lost",
//                language = "English",
//                createdDate = "2025-11-05T23:00:00.000000",
//            ),
//            Post(
//                id = "3",
//                imageUrl = "https://images.unsplash.com/photo-1501594907352-04cda38ebc29",
//                text = "The ocean stirs the heart, inspires the imagination",
//                language = "English",
//                createdDate = "2025-11-05T20:00:00.000000",
//            ),
//            Post(
//                id = "4",
//                imageUrl = "https://images.unsplash.com/photo-1493246507139-91e8fad9978e",
//                text = "Good food, good mood",
//                language = "English",
//                createdDate = "2025-11-05T16:00:00.000000",
//            ),
//            Post(
//                id = "5",
//                imageUrl = "https://images.unsplash.com/photo-1469474968028-56623f02e42e",
//                text = "Adventure awaits in the wild",
//                language = "English",
//                createdDate = "2025-11-05T04:00:00.000000",
//            ),
//            Post(
//                id = "6",
//                imageUrl = "https://images.unsplash.com/photo-1480714378408-67cf0d13bc1b",
//                text = "City lights, endless nights",
//                language = "English",
//                createdDate = "2025-11-05T04:00:00.000000",
//                username = "city_lights",
//                timeAgo = "1 day ago",
//                caption = "Urban photography at its finest 🏙️📸",
//                likes = 945,
//                comments = 32
//            ),
//            Post(
//                id = "7",
//                imageUrl = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131",
//                text = "Life is better with a furry friend",
//                language = "English",
//                createdDate = "2025-11-04T04:00:00.000000",
//                username = "pet_lover",
//                timeAgo = "2 days ago",
//                caption = "My furry best friend 🐱❤️",
//                likes = 4532,
//                comments = 234
//            ),
//            Post(
//                id = "8",
//                imageUrl = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b",
//                text = "Touch the sky, reach your dreams",
//                language = "English",
//                createdDate = "2025-11-03T04:00:00.000000",
//                username = "peak_seekers",
//                timeAgo = "3 days ago",
//                caption = "Above the clouds ⛰️☁️",
//                likes = 2987,
//                comments = 112
//            )
//        )
//    }
}

