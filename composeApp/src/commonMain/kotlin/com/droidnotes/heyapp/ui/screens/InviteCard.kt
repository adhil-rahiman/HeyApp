package com.droidnotes.heyapp.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import heyapp.composeapp.generated.resources.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/* ─────────────────  DATA  ───────────────── */

data class InviteCard(
    val id: String,
    val title: String,
    val subtitle: String,
    val image: DrawableResource,
)

private val dummyInvites = listOf(
    InviteCard("1", "Rooftop Yoga", "6:30 AM · 3 mutuals", Res.drawable.profile_placeholder),
    InviteCard("2", "Indie Pop Jam", "Tonight · Koramangala", Res.drawable.profile_placeholder),
    InviteCard("3", "Design Brunch", "Sun 10 AM · 2 km", Res.drawable.profile_placeholder),
)

/* ─────────────────  ROOT DECK  ───────────────── */

@Composable
fun InviteSwipeDeck(
    invites: List<InviteCard> = dummyInvites,
    onAccept: (InviteCard) -> Unit = {},
    onReject: (InviteCard) -> Unit = {},
) {
    var index by remember { mutableStateOf(0) }

    if (index >= invites.size) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No more invites", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    val current = invites[index]
    val next = invites.getOrNull(index + 1)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Under‑card preview
        if (next != null) CardLayer(card = next, scale = 0.93f, alpha = 0.85f)

        // Active card
        SwipeCard(card = current) { dir ->
            if (dir == SwipeDirection.RIGHT) onAccept(current) else onReject(current)
            index += 1
        }
    }
}

/* ─────────────────  SWIPE CARD  ───────────────── */

private enum class SwipeDirection { LEFT, RIGHT }

@Composable
private fun SwipeCard(
    card: InviteCard,
    onFinalSwipe: (SwipeDirection) -> Unit,
) {
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val rotationFactor = 20f
    val threshold = 220f

    Card(
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .fillMaxHeight(0.78f)
            .graphicsLayer {
                translationX = offsetX.value
                rotationZ = (offsetX.value / 40).coerceIn(-rotationFactor, rotationFactor)
            }
            .clip(RoundedCornerShape(24.dp))
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        scope.launch {
                            when {
                                offsetX.value > threshold -> {
                                    launchSwipe(offsetX, 1200f) { onFinalSwipe(SwipeDirection.RIGHT) }
                                }
                                offsetX.value < -threshold -> {
                                    launchSwipe(offsetX, -1200f) { onFinalSwipe(SwipeDirection.LEFT) }
                                }
                                else -> {
                                    offsetX.animateTo(0f, spring(stiffness = Spring.StiffnessMedium))
                                }
                            }
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    scope.launch {
                        offsetX.snapTo(offsetX.value + dragAmount)
                    }
                }
            },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        InviteCardContent(card)
    }
}

private suspend fun launchSwipe(anim: Animatable<Float, *>, target: Float, onEnd: () -> Unit) {
    anim.animateTo(target, tween(durationMillis = 260))
    onEnd()
    anim.snapTo(0f)
}

@Composable
private fun CardLayer(card: InviteCard, scale: Float, alpha: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .fillMaxHeight(0.78f)
            .graphicsLayer { this.scaleX = scale; this.scaleY = scale; this.alpha = alpha },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        InviteCardContent(card)
    }
}

@Composable
private fun InviteCardContent(card: InviteCard) {
    Box(Modifier.fillMaxSize()) {
        Image(
            painterResource(card.image),
            null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        1f to Color.Black.copy(alpha = 0.55f)
                    )
                )
        )
        Column(
            Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(card.title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Text(card.subtitle, color = Color.White.copy(0.75f), fontSize = 14.sp)
        }
    }
}
