package com.example.museumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

import com.example.museumapp.data.remote.dto.MuseumItem
import com.example.museumapp.room.FavoriteDatabase
import com.example.museumapp.room.FavoriteItem
import com.example.museumapp.room.FavoriteItemViewModel
import com.example.museumapp.room.FavoriteItemViewModelFactory
import com.example.museumapp.ui.theme.MuseumAppTheme

class MainActivity : ComponentActivity() {

    //use dependency injection in view model
//    private val service = MuseumService.create()
    private lateinit var viewModel: FavoriteItemViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, FavoriteItemViewModelFactory())
            .get(FavoriteItemViewModel::class.java)
    }

    @Composable
    fun MuseumAppContent() {
        val favoriteItems by viewModel.favoriteItems.collectAsState(emptyList())
        var newItemName by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    MuseumTitle()
                    Spacer(modifier = Modifier.height(16.dp))
                    AddFavoriteItemInput(
                        newItemName,
                        onItemNameChange = { newItemName = it })
                    Spacer(modifier = Modifier.height(16.dp))
                    AddFavoriteItemButton(newItemName) {
                        val newItem =
                            FavoriteItem(
                                itemId = favoriteItems.size + 1,
                                itemName = newItemName
                            )
//                        insertNewItemToDatabase(newItem)
                        viewModel.insertFavoriteItem(newItem)
                        newItemName = ""
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    FavoriteItemList(favoriteItems)

                }
            }
        }
    }

    @Composable
    fun FavoriteItemList(favoriteItems: List<FavoriteItem>) {
        LazyColumn {
            items(favoriteItems) { item ->
                FavoriteItemCard(item)

            }
        }
    }

    @Composable
    fun FavoriteItemCard(item: FavoriteItem) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = item.itemName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
//                        runBlocking(Dispatchers.IO) {
//                            museumRepository.deleteFavoriteItem(item)
//                        }
                        viewModel.deleteFavoriteItem(item)
                    },
                    colors = ButtonDefaults.buttonColors(

                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Remove from Favorites", color = Color.White)
                }
            }
        }
    }

    @Composable
    fun AddFavoriteItemButton(itemName: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            enabled = itemName.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add to Favorites", color = Color.White)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddFavoriteItemInput(itemName: String, onItemNameChange: (String) -> Unit) {
        TextField(
            value = itemName,
            onValueChange = { onItemNameChange(it) },
            label = { Text(text = "Enter Item Name") },
            modifier = Modifier.fillMaxWidth()
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MuseumTitle() {
        Text(
            text = "Favorite List",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            color = Color.Black
        )


        setContent {
//            val museum = produceState<List<MuseumItem>>(
//                initialValue = emptyList(),
//                producer = {
//                    try {
//                        value = service.getMuseumArt()
//
//                    } catch (e: Exception) {
//                        // Handle errors here, e.g., show an error message
//                        // and set an appropriate value for museumData
//                    }
//                }
//            )


//            MuseumAppTheme {
//                Surface {
//                    LazyColumn {
//                        items(museum.value) {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp)
//                            ) {
//                                val painter =
//                                    rememberAsyncImagePainter(
//                                        ImageRequest.Builder(LocalContext.current)
//                                            .data(data = it.images)
//                                            .apply(block = fun ImageRequest.Builder.() {
//                                                // You can configure image loading options here if needed
//                                            }).build()
//                                    )
//                                Image(
//                                    painter = painter,
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .height(200.dp), // Adjust the height as needed
//                                    contentScale = ContentScale.Crop, // Adjust the content scale as needed
//                                    alignment = Alignment.TopStart,
//                                )
//                                Text(text = it.title, fontSize = 20.sp)
//                                Spacer(modifier = Modifier.height(4.dp))
//                                Text(text = it.year, fontSize = 14.sp)
//                                Spacer(modifier = Modifier.height(4.dp))
//                            }
//
//
//                        }
//                    }
            MuseumAppContent()
        }
    }
}
