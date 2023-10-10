package com.example.museumapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import kotlinx.coroutines.launch
import androidx.compose.material3.Icon
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import com.example.museumapp.R
import androidx.compose.runtime.remember
import android.content.res.Configuration
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.museumapp.viewModel.FavouriteViewModel


@ExperimentalFoundationApi
@Composable
fun FavouriteAnimatedView(favouriteViewModel: FavouriteViewModel) {
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

    // Observe the LiveData
    val favouriteItems by favouriteViewModel.getAllFavourites().observeAsState(emptyList())

    val pagerState = rememberPagerState(
        pageCount = { favouriteItems.size },
        initialPage = 0
    )

    val scope = rememberCoroutineScope()

    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val backgroundImage = painterResource(id = R.drawable.museum)
        val backgroundImageRotate = painterResource(id = R.drawable.museumrotate)

        Image(
            painter = if (isLandscape) backgroundImageRotate else backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .scale(if (isLandscape) 4.8f else 2.3f)
                .offset(offsetX, offsetY)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {page ->

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = favouriteItems[page].images)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp)
                )
            }
            Box(
                modifier = Modifier
                    .offset(y = -(16).dp)
                    .fillMaxWidth(0.5f)
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage - 1
                            )
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                        .clip(RoundedCornerShape(100))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Go back"
                    )
                }
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(100))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Go forward"
                    )
                }
            }
        }
    }
}
