package com.example.museumapp.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.museumapp.data.remote.dto.MuseumItem

@Composable
fun ArtList(museumItems: List<MuseumItem>) {
    Surface {
        LazyColumn {
            items(museumItems) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    val painter =
                        rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = item.images).apply(block = fun ImageRequest.Builder.() {
                                    // You can configure image loading options here if needed
                                }).build()
                        )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), // Adjust the height as needed
                        contentScale = ContentScale.Crop, // Adjust the content scale as needed
                        alignment = Alignment.TopStart,
                    )
                    Text(text = item.title, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "${item.year}", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}