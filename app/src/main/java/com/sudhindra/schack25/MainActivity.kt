package com.sudhindra.schack25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sudhindra.schack25.ui.components.PostCard
import com.sudhindra.schack25.ui.components.PostCardData
import com.sudhindra.schack25.ui.theme.SCHack25Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SCHack25Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        PostCard(
                            data = PostCardData(
                                imageUrl = "https://www.figma.com/api/mcp/asset/5ae572ea-308a-4378-a969-f370e412ad50",
                                timeAgo = "2 hours ago",
                                caption = "Exploring the city streets at golden hour 🌆",
                                likes = 1247,
                                comments = 43,
                            )
                        )
                    }
                }
            }
        }
    }
}
