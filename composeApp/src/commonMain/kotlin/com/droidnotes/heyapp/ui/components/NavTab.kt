package com.droidnotes.heyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

enum class NavTab(val route: String, val icon: ImageVector) {
    FEED("feed", Icons.Default.Home),
    SWIPE("swipe", Icons.Default.Explore),
    SHOP("shop", Icons.Default.ShoppingCart),
    GRAPH("graph", Icons.Default.Map)
}

@Composable
fun BottomNav(current: String, onSelect: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavTab.entries.forEach { tab ->
            val neon = if (tab.route == current) Color(0xFFFF0055) else Color(0xFF444444)
            IconButton(onClick = { onSelect(tab.route) }) {
                Icon(tab.icon, tab.route, tint = neon)
            }
        }
    }
}
