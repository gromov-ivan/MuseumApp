package com.example.museumapp.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.museumapp.room.FavouriteItem

@Composable
fun FavouriteItemCard(
    favouriteItem: FavouriteItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
        ) {
            // Display the image using an Image composable

            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = favouriteItem.images)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(2.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(10.dp))
            Column (
                modifier = Modifier.weight(0.7f), // Let the text occupy 70% of the available space
            ) {
                Text(
                    text = "${favouriteItem.title}.",
                    fontSize = 17.sp,
                    modifier = Modifier
                        .widthIn(max = 200.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = favouriteItem.nonPresenterAuthorsName.trim().takeIf { it.isNotEmpty() } ?: "Unknown artist", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Add a favorite icon button

        }
    }
}
