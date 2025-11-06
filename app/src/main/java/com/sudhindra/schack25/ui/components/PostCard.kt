package com.sudhindra.schack25.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sudhindra.schack25.data.model.Post
import com.sudhindra.schack25.ui.theme.SCHack25Theme
import java.time.Duration
import java.time.Instant

/**
 * Data class representing a social media post
 */
data class PostCardData(
    val imageUrl: String,
    val username: String = "Devotional Post Bot",
    val timeAgo: String,
    val text: String = "",
)

/**
 * PostCard composable - A social media style card component
 * Based on Figma design node: 1:234
 */
@Composable
fun PostCard(
    data: PostCardData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 0.566.dp,
            color = Color.Black.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Image Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(356.dp)
            ) {
                AsyncImage(
                    model = data.imageUrl,
                    contentDescription = "Post image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                // Text overlay
                if (data.text.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(16.dp)
                            .align(Alignment.BottomCenter),
                        text = data.text,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    )
                }
            }

            // Content Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Profile Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar
                    AsyncImage(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        model = "https://www.devpoojan.in/cdn/shop/articles/HD-Lord-Ram-Pictures.jpg?v=1679112776&width=540",
                        contentDescription = "User avatar",
                        contentScale = ContentScale.Crop
                    )

                    // Username and Time
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        // Username with verified badge
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = data.username,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF0A0A0A),
                                letterSpacing = (-0.3125).sp
                            )
                        }

                        // Time ago
                        Text(
                            text = data.timeAgo,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF717182),
                            lineHeight = 16.sp
                        )
                    }
                }

                // Stats Section
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Spacer(modifier = Modifier.height(0.566.dp))

                    // Likes and Comments Badges
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        val likes = rememberSaveable { (20..500).random() }
                        val comments = rememberSaveable { (5..100).random() }
                        // Likes Badge
                        StatBadge(
                            icon = "❤️",
                            count = formatCount(likes),
                            backgroundColor = Color(0xFFFF6B6B).copy(alpha = 0.13f),
                            textColor = Color(0xFFFF6B6B)
                        )

                        // Comments Badge
                        StatBadge(
                            icon = "💬",
                            count = comments.toString(),
                            backgroundColor = Color(0xFFFF6B6B).copy(alpha = 0.13f),
                            textColor = Color(0xFFFF6B6B)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Stat badge component for likes and comments
 */
@Composable
private fun StatBadge(
    icon: String,
    count: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
//            .height(25.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(19003600.dp)
            )
            .border(
                width = 0.566.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(19003600.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            fontSize = 12.sp
        )

        Text(
            text = count,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            lineHeight = 16.sp
        )
    }
}

/**
 * Format count with commas (e.g., 1247 -> 1,247)
 */
@SuppressLint("DefaultLocale")
private fun formatCount(count: Int): String {
    return String.format("%,d", count)
}

/**
 * Calculate time ago from ISO 8601 timestamp
 * Format: 2025-11-06T06:01:49.346623
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun calculateTimeAgo(createdDate: String): String {
    return try {
        // Parse the ISO 8601 timestamp
        val instant = Instant.parse(createdDate.replace(" ", "T").let {
            // Ensure it ends with Z if no timezone info
            if (it.contains("+") || it.contains("Z")) it else "${it}Z"
        })
        
        val now = Instant.now()
        val duration = Duration.between(instant, now)
        
        when {
            duration.toMinutes() < 1 -> "Just now"
            duration.toMinutes() < 60 -> "${duration.toMinutes()} minute${if (duration.toMinutes() != 1L) "s" else ""} ago"
            duration.toHours() < 24 -> "${duration.toHours()} hour${if (duration.toHours() != 1L) "s" else ""} ago"
            duration.toDays() < 7 -> "${duration.toDays()} day${if (duration.toDays() != 1L) "s" else ""} ago"
            duration.toDays() < 30 -> "${duration.toDays() / 7} week${if (duration.toDays() / 7 != 1L) "s" else ""} ago"
            duration.toDays() < 365 -> "${duration.toDays() / 30} month${if (duration.toDays() / 30 != 1L) "s" else ""} ago"
            else -> "${duration.toDays() / 365} year${if (duration.toDays() / 365 != 1L) "s" else ""} ago"
        }
    } catch (e: Exception) {
        "Recently"
    }
}

/**
 * Extension function to convert API Post to PostCardData
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Post.toPostCardData(): PostCardData {
    return PostCardData(
        imageUrl = this.imageUrl,
        timeAgo = calculateTimeAgo(this.createdDate),
        text = this.text,
    )
}

/**
 * Overloaded PostCard that accepts Post model
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostCard(
    post: Post,
    modifier: Modifier = Modifier
) {
    PostCard(
        data = post.toPostCardData(),
        modifier = modifier
    )
}

/**
 * Preview of the PostCard component
 */
@Preview(showBackground = true)
@Composable
fun PostCardPreview() {
    SCHack25Theme {
        PostCard(
            data = PostCardData(
                imageUrl = "https://www.figma.com/api/mcp/asset/5ae572ea-308a-4378-a969-f370e412ad50",
                timeAgo = "2 hours ago",
                text = "The best views come after the hardest climbs",
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

