package com.example.museumapp.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.museumapp.data.remote.dto.MuseumItem

@Composable
fun CollectionDetailView(selectedItem: MuseumItem) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = selectedItem.images).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
    )
    Box(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .scale(0.8f),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopStart,
            )
            Text(text = "${selectedItem.title}, ${selectedItem.year}.",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 30.dp, top = 2.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Artist: ${
                    selectedItem.nonPresenterAuthorsName.trim()
                        .takeIf { it.isNotEmpty() } ?: "Unknown artist."
                }",

                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 30.dp, top = 2.dp, end = 30.dp, bottom = 10.dp))
            Text(
                text = "${selectedItem.imageDescription}",
                modifier = Modifier
                    .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 30.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))

//            IconButton(
//                onClick = {
//                    favouriteItemViewModel?.let { viewModel ->
//                        val favouriteItem = FavouriteItem(
//                            itemId = selectedItem.id,
//                            itemName = selectedItem.name
//                        )
//                        viewModel.insertFavouriteItem(favouriteItem)
//
////                        SnackbarHostState(scope = rememberCoroutineScope()).showSnackbar(
////                            message = "Added to favorites",
////                            actionLabel = "Undo"
////                        )
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp)
//            ) {
//                Text(text = "Add to favourites", color = Color.White)
//            }
        }
    }
}
