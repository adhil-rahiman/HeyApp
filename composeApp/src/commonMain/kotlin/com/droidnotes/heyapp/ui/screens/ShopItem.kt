package com.droidnotes.heyapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidnotes.heyapp.ui.components.NeonIcon
import heyapp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/* -------------------------------------------------------- */
/*  DATA MODELS                                             */
/* -------------------------------------------------------- */

sealed class ShopCardItem {
    data class ImageCard(val title: String, val image: DrawableResource) : ShopCardItem()
    data class TextCard(val text: String) : ShopCardItem()
}

val shopCards = listOf(
    ShopCardItem.ImageCard("Satin Slip Dress", Res.drawable.product_placeholder),
    ShopCardItem.TextCard("🔥 Trending Picks"),
    ShopCardItem.ImageCard("Red Bodysuit", Res.drawable.product_placeholder),
    ShopCardItem.ImageCard("Velvet Top", Res.drawable.product_placeholder),
    ShopCardItem.TextCard("⭐ Editor's Pick"),
    ShopCardItem.ImageCard("Midnight Gown", Res.drawable.product_placeholder),
    ShopCardItem.ImageCard("Cozy Knitwear", Res.drawable.product_placeholder),
    ShopCardItem.ImageCard("Classic Trench", Res.drawable.product_placeholder)
)

/* -------------------------------------------------------- */
/*  STAGGERED GRID LAYOUT (Unified Scroll)                  */
/* -------------------------------------------------------- */

@Composable
fun ShopDiscoverScreen() {
    val scroll = rememberScrollState()
    val column1 = remember { shopCards.filterIndexed { index, _ -> index % 2 == 0 } }
    val column2 = remember { shopCards.filterIndexed { index, _ -> index % 2 != 0 } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(scroll)
            .padding(16.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Discover",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                NeonIcon(Icons.Default.FavoriteBorder)
                NeonIcon(Icons.Default.ShoppingCart)
                NeonIcon(Icons.Default.Person)
            }
        }

        // Staggered Grid (two columns in one scroll)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                column1.forEach { item ->
                    when (item) {
                        is ShopCardItem.ImageCard -> ProductCard(item)
                        is ShopCardItem.TextCard -> TextLabelCard(item)
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                column2.forEach { item ->
                    when (item) {
                        is ShopCardItem.ImageCard -> ProductCard(item)
                        is ShopCardItem.TextCard -> TextLabelCard(item)
                    }
                }
            }
        }
    }
}


/* -------------------------------------------------------- */
/*  CARD COMPOSABLES                                        */
/* -------------------------------------------------------- */

@Composable
private fun ProductCard(item: ShopCardItem.ImageCard) {
    Box(
        modifier = Modifier
            .aspectRatio(0.75f)
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(16.dp))
            .background(Color.Black, RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        1f to Color.Black.copy(alpha = 0.6f)
                    )
                )
        )
        Text(
            text = item.title,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}

@Composable
private fun TextLabelCard(item: ShopCardItem.TextCard) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFFFF0055), Color(0xFFFF66A5))
                ),
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}
