package com.droidnotes.heyapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.droidnotes.heyapp.ui.components.BottomNav

@Composable
fun HeyHomeWithNav() {
    val navController = rememberNavController()
    var currentTab by remember { mutableStateOf("feed") }

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            BottomNav(current = currentTab) { route ->
                currentTab = route
                navController.navigate(route) {
                    popUpTo("feed") { inclusive = false }
                    launchSingleTop = true
                }
            }
        }
    ) { inner ->
        Box(Modifier.padding(inner)) {
            HeyNavGraph(navController)
        }
    }
}
