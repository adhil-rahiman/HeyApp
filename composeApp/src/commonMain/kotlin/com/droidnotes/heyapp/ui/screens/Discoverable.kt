package com.droidnotes.heyapp.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import heyapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen2() {
    val listState = rememberLazyGridState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Hey",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filter")
                    }
                }
            )
        },
        containerColor = Color(0xFFFDFDFD)
    ) { inner ->
        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(inner)
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(sampleDiscoverables) { d ->
                DiscoverableCardMinimal(d)
            }
        }
    }
}

@Composable
private fun DiscoverableCardMinimal(d: Discoverable) {
    var expanded by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(if (expanded) 12.dp else 2.dp, tween(240))
    val scale by animateDpAsState(if (expanded) 1.02.dp else 1.0.dp, tween(240))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
            .graphicsLayer { scaleX = scale.value; scaleY = scale.value }
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(elevation),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(d.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
            ) {
                if (d.isLive) LiveBadge()
                Text(
                    text = d.title,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = d.subtitle,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun LiveBadge() {
    Text(
        text = "LIVE",
        color = Color.White,
        fontSize = 10.sp,
        modifier = Modifier
            .background(Color(0xFFE53935), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    )
}

internal data class Discoverable(
    val id: String,
    val title: String,
    val subtitle: String,
    val image: DrawableResource,
    val isLive: Boolean = false,
    val mutualCount: Int = 0,
)

private val sampleDiscoverables = listOf(
    Discoverable("1", "Yoga in Cubbon", "3 mutuals • Today 6 AM", Res.drawable.profile_placeholder, true, 3),
    Discoverable("2", "Indie Pop Jam", "Bangalore • 2 mutual DJs", Res.drawable.profile_placeholder),
    Discoverable("3", "Design Brunch", "Sunday 10 AM", Res.drawable.profile_placeholder, mutualCount = 1),
    Discoverable("4", "Slow Dating Café", "Koramangala • Tonight", Res.drawable.profile_placeholder, isLive = true, mutualCount = 5)
)
