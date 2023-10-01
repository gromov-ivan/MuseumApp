package com.example.museumapp.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.museumapp.viewModel.MuseumViewModel
import com.example.museumapp.data.remote.dto.MuseumItem

@Composable
fun CollectionList(
    museumItems: List<MuseumItem>,
    viewModel: MuseumViewModel,
    selectedCard: String,
    ) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = when (selectedCard) {
        "Tuusula Museum" -> listOf("Drawings", "Pictures")
        "Ateneum Museum" -> listOf("Graphics", "Sculptures")
        "Photography Museum" -> listOf("Cities", "Agriculture")
        else -> emptyList()
    }

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
                        // call corresponding function
                        when (selectedCard) {
                            "Tuusula Museum" -> {
                                when (index) {
                                    0 -> viewModel.fetchTuusulaDrawings()
                                    1 -> viewModel.fetchTuusulaPictures()
                                }
                            }
                            "Ateneum Museum" -> {
                                when (index) {
                                    0 -> viewModel.fetchAteneumGraphics()
                                    1 -> viewModel.fetchAteneumSculptures()
                                }
                            }
                            "Photography Museum" -> {
                                when (index) {
                                    0 -> viewModel.fetchCitiesPhotograhs()
                                    1 -> viewModel.fetchAgriculturePhotographs()
                                }
                            }
                        }

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
                    }
                    1 -> {
                        Text(text = "Tuusula Museum - Pictures collection")
                    }
                }
            }
            "Ateneum Museum" -> {
                when (selectedTabIndex) {
                    0 -> {
                        Text(text = "Ateneum Museum - Graphics collection")
                    }
                    1 -> {
                        Text(text = "Ateneum Museum - Sculpture collection")
                    }
                }
            }
            "Photography Museum" -> {
                when (selectedTabIndex) {
                    0 -> {
                        Text(text = "Photography Museum - Cities collection")
                    }
                    1 -> {
                        Text(text = "Photography Museum - Agriculture collection")
                    }
                }
            }
        }

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