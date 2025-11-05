# API Setup Guide

## 🎉 Current Status: Using Mock Data

The app is currently using **mock data** to test the flow. You can run the app right now and see 8 sample posts with:
- Beautiful images from Unsplash
- Various usernames and captions
- Different like and comment counts
- Pull-to-refresh functionality
- Loading states

## Overview
The app is configured to fetch posts from a REST API and display them in a scrollable list.

## Configuration Required

### 1. Update API Base URL

Edit the file: `app/src/main/java/com/sudhindra/schack25/data/api/RetrofitClient.kt`

```kotlin
private const val BASE_URL = "https://your-api-url.com/api/"
```

Replace `"https://your-api-url.com/api/"` with your actual API base URL.

### 2. Configure API Endpoint

Edit the file: `app/src/main/java/com/sudhindra/schack25/data/api/ApiService.kt`

Update the endpoint path and parameters as needed:

```kotlin
@GET("posts")  // Change "posts" to your actual endpoint
suspend fun getPosts(
    @Query("limit") limit: Int? = null,
    @Query("offset") offset: Int? = null
): PostResponse
```

### 3. Expected JSON Response Format

Your API should return JSON in this format:

```json
{
  "posts": [
    {
      "id": "1",
      "image_url": "https://example.com/image.jpg",
      "username": "Devotional Post Bot",
      "time_ago": "2 hours ago",
      "caption": "Exploring the city streets at golden hour 🌆",
      "likes": 1247,
      "comments": 43,
      "avatar_url": "https://example.com/avatar.jpg"
    }
  ]
}
```

### 4. Adjust Data Model (If Needed)

If your API uses different field names, update the `@SerializedName` annotations in:
`app/src/main/java/com/sudhindra/schack25/data/model/Post.kt`

Example:
```kotlin
@SerializedName("image_url")  // Change this to match your API field name
val imageUrl: String,
```

### 5. Add Authentication (If Required)

If your API requires authentication, add an interceptor in `RetrofitClient.kt`:

```kotlin
private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer YOUR_API_TOKEN")
            .build()
        chain.proceed(request)
    }
    .addInterceptor(loggingInterceptor)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()
```

## Features Implemented

✅ **LazyColumn** - Scrollable list of posts
✅ **Pull-to-Refresh** - Swipe down to refresh the posts
✅ **Loading State** - Shows spinner while fetching data
✅ **Error Handling** - Displays error message if API call fails
✅ **MVVM Architecture** - Clean separation of concerns
✅ **Async Image Loading** - Images load asynchronously with Coil

## Switching from Mock Data to Real API

The app is currently using mock data. To switch to a real API:

1. Open `PostViewModel.kt`
2. In the `loadPosts()` function, **comment out** the mock data section:
   ```kotlin
   // MOCK DATA - Replace with actual API call when ready
   // val mockPosts = getMockPosts()
   // _uiState.value = PostUiState.Success(mockPosts)
   ```

3. **Uncomment** the real API call:
   ```kotlin
   repository.getPosts()
       .onSuccess { posts ->
           _uiState.value = PostUiState.Success(posts)
       }
       .onFailure { exception ->
           _uiState.value = PostUiState.Error(
               exception.message ?: "Unknown error occurred"
           )
       }
   ```

4. Make sure you've configured the API URL in `RetrofitClient.kt` first!

## File Structure

```
app/src/main/java/com/sudhindra/schack25/
├── data/
│   ├── api/
│   │   ├── ApiService.kt          # API endpoint definitions
│   │   └── RetrofitClient.kt      # Retrofit configuration
│   ├── model/
│   │   └── Post.kt                # Data models
│   └── repository/
│       └── PostRepository.kt      # Data repository
├── ui/
│   ├── components/
│   │   └── PostCard.kt            # Post card UI component
│   ├── theme/                     # Theme files
│   └── viewmodel/
│       └── PostViewModel.kt       # ViewModel with state management
└── MainActivity.kt                # Main screen with LazyColumn
```

## Next Steps

1. Get your API endpoint URL
2. Update `RetrofitClient.kt` with the base URL
3. Update `ApiService.kt` with the correct endpoint path
4. Verify the JSON response format matches the expected format
5. Run the app and test!

## Troubleshooting

- **"Unable to resolve host"**: Check your BASE_URL is correct
- **"HTTP 404"**: Verify the endpoint path in ApiService.kt
- **No data showing**: Check LogCat for API response errors
- **Images not loading**: Ensure INTERNET permission is in AndroidManifest.xml (already added)

