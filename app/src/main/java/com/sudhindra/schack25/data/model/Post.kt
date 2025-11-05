package com.sudhindra.schack25.data.model

import com.google.gson.annotations.SerializedName

/**
 * API response model for posts
 */
data class PostResponse(
    @SerializedName("posts")
    val posts: List<Post>
)

/**
 * Post model from API
 */
data class Post(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("image_url")
    val imageUrl: String,
    
    @SerializedName("username")
    val username: String = "Devotional Post Bot",
    
    @SerializedName("time_ago")
    val timeAgo: String,
    
    @SerializedName("caption")
    val caption: String,
    
    @SerializedName("likes")
    val likes: Int,
    
    @SerializedName("comments")
    val comments: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQge_0nK5z5DQ85QdN-ANHiVcwJGcQc4n8ZpQ&s"
)

