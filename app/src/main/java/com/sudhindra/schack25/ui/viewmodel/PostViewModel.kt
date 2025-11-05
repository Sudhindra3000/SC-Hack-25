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
            val mockPosts = getMockPosts()
            _uiState.value = PostUiState.Success(mockPosts)
            
            // Uncomment below to use real API
            /*
            repository.getPosts()
                .onSuccess { posts ->
                    _uiState.value = PostUiState.Success(posts)
                }
                .onFailure { exception ->
                    _uiState.value = PostUiState.Error(
                        exception.message ?: "Unknown error occurred"
                    )
                }
            */
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
    private fun getMockPosts(): List<Post> {
        return listOf(
            Post(
                id = "1",
                imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4",
                username = "nature_lover",
                timeAgo = "2 hours ago",
                caption = "Beautiful mountain sunrise 🏔️✨",
                likes = 1247,
                comments = 43,
                avatarUrl = "https://i.pravatar.cc/150?img=1"
            ),
            Post(
                id = "2",
                imageUrl = "https://images.unsplash.com/photo-1511300636408-a63a89df3482",
                username = "travel_wanderer",
                timeAgo = "5 hours ago",
                caption = "Exploring ancient temples in Asia 🛕",
                likes = 892,
                comments = 27,
                avatarUrl = "https://i.pravatar.cc/150?img=2"
            ),
            Post(
                id = "3",
                imageUrl = "https://images.unsplash.com/photo-1501594907352-04cda38ebc29",
                username = "ocean_vibes",
                timeAgo = "8 hours ago",
                caption = "Sunset by the beach never gets old 🌅🌊",
                likes = 2156,
                comments = 89,
                avatarUrl = "https://i.pravatar.cc/150?img=3"
            ),
            Post(
                id = "4",
                imageUrl = "https://images.unsplash.com/photo-1493246507139-91e8fad9978e",
                username = "foodie_adventures",
                timeAgo = "12 hours ago",
                caption = "The perfect breakfast spread 🥐☕",
                likes = 3421,
                comments = 156,
                avatarUrl = "https://i.pravatar.cc/150?img=4"
            ),
            Post(
                id = "5",
                imageUrl = "https://images.unsplash.com/photo-1469474968028-56623f02e42e",
                username = "wild_explorer",
                timeAgo = "1 day ago",
                caption = "Lost in the wilderness 🌲🏕️",
                likes = 1876,
                comments = 64,
                avatarUrl = "https://i.pravatar.cc/150?img=5"
            ),
            Post(
                id = "6",
                imageUrl = "https://images.unsplash.com/photo-1480714378408-67cf0d13bc1b",
                username = "city_lights",
                timeAgo = "1 day ago",
                caption = "Urban photography at its finest 🏙️📸",
                likes = 945,
                comments = 32,
                avatarUrl = "https://i.pravatar.cc/150?img=6"
            ),
            Post(
                id = "7",
                imageUrl = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131",
                username = "pet_lover",
                timeAgo = "2 days ago",
                caption = "My furry best friend 🐱❤️",
                likes = 4532,
                comments = 234,
                avatarUrl = "https://i.pravatar.cc/150?img=7"
            ),
            Post(
                id = "8",
                imageUrl = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b",
                username = "peak_seekers",
                timeAgo = "3 days ago",
                caption = "Above the clouds ⛰️☁️",
                likes = 2987,
                comments = 112,
                avatarUrl = "https://i.pravatar.cc/150?img=8"
            )
        )
    }
}

