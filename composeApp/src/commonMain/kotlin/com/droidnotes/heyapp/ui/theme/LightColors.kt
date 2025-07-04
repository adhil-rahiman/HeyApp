package com.droidnotes.heyapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.material3.lightColorScheme
import com.droidnotes.heyapp.ui.components.Colors

private val LightColors = lightColorScheme(
    primary = Colors.primary,
    secondary = Colors.secondary,
    background = Colors.background,
    surface = Colors.surface,
    onPrimary = Colors.onPrimary,
    onSecondary = Colors.onSecondary,
    onBackground = Colors.onBackground,
    onSurface = Colors.onSurface
)

@Composable
fun HeyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
