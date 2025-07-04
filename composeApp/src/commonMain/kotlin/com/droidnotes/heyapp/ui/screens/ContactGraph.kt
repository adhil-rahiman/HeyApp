package com.droidnotes.heyapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.droidnotes.heyapp.model.Contact
import kotlin.math.*

@Composable
fun ContactGraph(
    contacts: List<Contact>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 3

            contacts.forEachIndexed { index, contact ->
                val angle = (2 * PI * index) / contacts.size
                val x = center.x + radius * cos(angle).toFloat()
                val y = center.y + radius * sin(angle).toFloat()

                drawCircle(
                    color = if (contact.isCloseFriend) Color(0xFF9575CD) else Color(0xFFB39DDB),
                    radius = 40f,
                    center = Offset(x, y)
                )
            }

            drawCircle(
                color = Color(0xFF7E57C2),
                radius = 48f,
                center = center
            )
        }

        Text(
            text = "You",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 4.dp),
            color = Color.White
        )
    }
}
