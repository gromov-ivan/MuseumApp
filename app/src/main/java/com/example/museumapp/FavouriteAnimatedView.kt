package com.example.museumapp

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun FavouriteAnimatedView() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f, // 360 degrees for a full circle
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val radius = 8.dp // Adjust the radius of the circular path

    val offsetX = remember(angle) {
        (radius.value * cos(Math.toRadians(angle.toDouble()))).dp
    }

    val offsetY = remember(angle) {
        (radius.value * sin(Math.toRadians(angle.toDouble()))).dp
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val backgroundImage = painterResource(id = R.drawable.museum)

        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Fit, // Fit the image within the Box
            modifier = Modifier
                .scale(2.3f) // Scale the image by 2.3
                .offset(offsetX, offsetY)
        )
    }
}
