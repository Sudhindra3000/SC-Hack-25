package com.sudhindra.schack25.ui.components

import android.annotation.SuppressLint
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

/**
 * Data class representing a social media post
 */
data class PostCardData(
    val imageUrl: String,
    val username: String = "Devotional Post Bot",
    val timeAgo: String,
    val caption: String,
    val quote: String = "",
    val likes: Int,
    val comments: Int,
    val avatarUrl: String = "https://upload.wikimedia.org/wikipedia/commons/5/52/Bangalore_Shiva.jpg"
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
                    .height(256.dp)
            ) {
                AsyncImage(
                    model = data.imageUrl,
                    contentDescription = "Post image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                // Quote overlay
                if (data.quote.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(16.dp)
                            .align(Alignment.BottomCenter),
                        text = data.quote,
                        fontSize = 14.sp,
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
                        model = data.avatarUrl,
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

                // Caption
                Text(
                    text = data.caption,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF0A0A0A),
                    lineHeight = 22.75.sp,
                    letterSpacing = (-0.1504).sp,
                    maxLines = 1
                )

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
                        // Likes Badge
                        StatBadge(
                            icon = "❤️",
                            count = formatCount(data.likes),
                            backgroundColor = Color(0xFFFF6B6B).copy(alpha = 0.13f),
                            textColor = Color(0xFFFF6B6B)
                        )

                        // Comments Badge
                        StatBadge(
                            icon = "💬",
                            count = data.comments.toString(),
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
 * Extension function to convert API Post to PostCardData
 */
fun Post.toPostCardData(): PostCardData {
    return PostCardData(
        imageUrl = this.imageUrl,
        username = this.username,
        timeAgo = this.timeAgo,
        caption = this.caption,
        quote = this.quote,
        likes = this.likes,
        comments = this.comments,
        avatarUrl = this.avatarUrl
    )
}

/**
 * Overloaded PostCard that accepts Post model
 */
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
                caption = "Exploring the city streets at golden hour 🌆",
                quote = "The best views come after the hardest climbs",
                likes = 1247,
                comments = 43,
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

