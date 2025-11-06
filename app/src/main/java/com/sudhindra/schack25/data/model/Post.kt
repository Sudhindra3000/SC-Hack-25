package com.sudhindra.schack25.data.model

import com.google.gson.annotations.SerializedName

/**
 * API response model for posts
 */
data class PostResponse(
    @SerializedName("status")
    val status: String,
    
    @SerializedName("count")
    val count: Int,
    
    @SerializedName("total")
    val total: Int,
    
    @SerializedName("data")
    val data: List<Post>
)

/**
 * Post model from API
 */
data class Post(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("image_url")
    val imageUrl: String,
    
    @SerializedName("text")
    val text: String,
    
    @SerializedName("language")
    val language: String,
    
    @SerializedName("created_date")
    val createdDate: String,
    
)

