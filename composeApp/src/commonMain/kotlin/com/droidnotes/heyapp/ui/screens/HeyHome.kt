package com.droidnotes.heyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.droidnotes.heyapp.ui.components.BottomNav

// Feed placeholder below

/* ───── NAV ENUM ───── */
enum class HomeTab(
    val icon: ImageVector,
    val label: String
) {
    FEED(Icons.Default.Home, "Feed"),
    SWIPE(Icons.Default.Explore, "Swipe"),
    SHOP(Icons.Default.ShoppingCart, "Shop"),
    GRAPH(Icons.Default.Map, "Graph")
}

/* ───── ROOT HOME ───── */
@Composable
fun HeyHome() {
    var current by remember { mutableStateOf(HomeTab.FEED.label) }

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            BottomNav(current = current) { chosen -> current = chosen }
        }
    ) { inner ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ) {
            when (current) {
                HomeTab.FEED.label  -> FeedPlaceholder()          // Replace with real feed composable
                HomeTab.SWIPE.label -> InviteSwipeDeck()
                HomeTab.SHOP.label  -> ShopDiscoverScreen()
                HomeTab.GRAPH.label -> SocialGraphScreen()
            }
        }
    }
}


/* ───── TEMP FEED PLACEHOLDER ───── */
@Composable
private fun FeedPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "24-Hour Feed coming soon",
            color = Color.White
        )
    }
}

