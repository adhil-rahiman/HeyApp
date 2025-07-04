// HeyNavGraph.kt
package com.droidnotes.heyapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun HeyNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "feed") {
        composable("feed") { FeedPlaceholder() }
        composable("swipe") { InviteSwipeDeck() }
        composable("shop")  { ShopDiscoverScreen() }
        composable("graph") { SocialGraphScreen() }
    }
}

@Composable
private fun FeedPlaceholder() {
    Text("Feed Coming Soon", color = Color.White)
}
