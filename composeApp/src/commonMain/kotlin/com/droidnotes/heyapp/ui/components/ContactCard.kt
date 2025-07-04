package com.droidnotes.heyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.droidnotes.heyapp.ui.theme.Spacing

@Composable
fun ContactCard(name: String = "Alex", modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 120.dp, height = 160.dp)
            .padding(Spacing.sm)
            .shadow(8.dp, RoundedCornerShape(20.dp))
            .background(Color.LightGray, RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name)
    }
}