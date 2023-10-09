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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.museumapp.data.remote.dto.FavouriteMuseumItem
import com.example.museumapp.data.remote.dto.MuseumItem
import com.example.museumapp.room.FavouriteItem
import com.example.museumapp.viewModel.FavouriteViewModel
import com.example.museumapp.viewModel.MuseumViewModel
import java.net.URLEncoder

@Composable
fun CollectionList(
    museumItems: List<MuseumItem>,
    viewModel: MuseumViewModel,
    selectedCard: String,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel
) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = when (selectedCard) {
        "Tuusula Museum" -> listOf("Drawings", "Pictures")
        "Ateneum Museum" -> listOf("Graphics", "Sculptures")
        "Photography Museum" -> listOf("Cities", "Agriculture")
        else -> emptyList()
    }
    //val favouriteItems = remember { mutableStateListOf<FavouriteItem>() }
    //val favoriteItemsList = favouriteViewModel.favoriteItemsList
    val favouriteItems by favouriteViewModel.favouriteItems.observeAsState(emptyList())
    // Initialize a map to store the isFavourite state for each item
    val isFavouriteMap = remember { mutableStateMapOf<String, Boolean>() }
    Column {
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTabIndex,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = { Text(text = title) }
                )
            }
        }

        // Content based on the selected tab
        when (selectedCard) {
            "Tuusula Museum" -> {
                when (selectedTabIndex) {
                    0 -> {
                        Text(text = "Tuusula Museum - Drawings collection")
                        viewModel.fetchTuusulaDrawings()
                    }
                    1 -> {
                        Text(text = "Tuusula Museum - Pictures collection")
                        viewModel.fetchTuusulaPictures()
                    }
                }
            }
            "Ateneum Museum" -> {
                when (selectedTabIndex) {
                    0 -> {
                        Text(text = "Ateneum Museum - Graphics collection")
                        viewModel.fetchAteneumGraphics()
                    }
                    1 -> {
                        Text(text = "Ateneum Museum - Sculpture collection")
                        viewModel.fetchAteneumSculptures()
                    }
                }
            }
            "Photography Museum" -> {
                when (selectedTabIndex) {
                    0 -> {
                        Text(text = "Photography Museum - Cities collection")
                        viewModel.fetchCitiesPhotograhs()
                    }
                    1 -> {
                        Text(text = "Photography Museum - Agriculture collection")
                        viewModel.fetchAgriculturePhotographs()
                    }
                }
            }
        }

        LazyColumn {
            items(museumItems) { item ->

                val isFavouriteState = isFavouriteMap[item.id] ?: false

                // Find the corresponding favorite item in the database and update isFavouriteState
                favouriteItems.find { it.id == item.id }?.let { favouriteItem ->
                    isFavouriteMap[item.id] = favouriteItem.isFavourite
                }
                //val isFavouriteState = remember { mutableStateOf(favouriteViewModel.isFavourite(item.id)) }
                //val isFavouriteState = remember { mutableStateOf(favouriteItems.any { it.id == item.id }) }
                //val isFavouriteState = remember { mutableStateOf(favouriteItems.any { it.id == item.id }) }
                // Check if the current item is in the list of favorite items
                //var isFavourite = remember { mutableStateOf(favoriteItemsList.any { it.id == item.id }) }

                //val isFavouriteState = remember { mutableStateOf(false) }

                // Find the corresponding favorite item in the database and set isFavouriteState accordingly
                /*favouriteItems.find { it.id == item.id }?.let {
                    isFavouriteState.value = it.isFavourite
                }*/

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp)
                        .clickable {
                            Log.d("MyApp", "Navigating to collectionDetailView/${item.id}")
                            // encode item.id to use the value in the navigation route
                            val encodedItemId = URLEncoder.encode(item.id, "UTF-8")
                            navController.navigate("collectionDetailView/${encodedItemId}")
                        },


                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                    ) {
                        val painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = item.images)
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
                        Spacer(modifier = Modifier.width(10.dp))
                        Column (
                            modifier = Modifier.weight(0.7f),
                        ) {
                            Text(
                                text = "${item.title}.",
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .widthIn(max = 200.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = item.nonPresenterAuthorsName.trim().takeIf { it.isNotEmpty() } ?: "Unknown artist", fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box {
                            IconButton(
                                onClick = {
                                    val newFavouriteState = !isFavouriteState
                                    isFavouriteMap[item.id] = newFavouriteState

                                    //isFavouriteState.value = !isFavouriteState.value

                                    val favouriteItem = FavouriteItem(
                                        id = item.id,
                                        images = item.images,
                                        imageDescription = item.imageDescription,
                                        nonPresenterAuthorsName = item.nonPresenterAuthorsName,
                                        title = item.title,
                                        year = item.year,
                                        isFavourite = newFavouriteState
                                    )

                                    if (newFavouriteState) {
                                        favouriteViewModel.saveFavoriteItem(favouriteItem)
                                        Log.d("DBG", "Marked item ${favouriteItem.id} as a favourite")
                                    } else {
                                        favouriteViewModel.deleteFavoriteItem(favouriteItem)
                                        Log.d("DBG", "Removed item ${favouriteItem.id} from favourites")
                                    }

                                    favouriteViewModel.updateFavoriteItem(favouriteItem)

                                },
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.TopEnd)
                            ) {
                                 val icon = if (isFavouriteState) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}