package com.example.museumapp.composable

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.viewModel.FavouriteViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouritesView(favouriteViewModel: FavouriteViewModel){
    val navController = rememberNavController()
    //val favouriteViewModel: FavouriteViewModel = viewModel()

    //Observe the LiveData
    //val favouriteItems = favouriteViewModel.getAllFavourites().observeAsState()

    // Observe the LiveData
    val favouriteItems by favouriteViewModel.getAllFavourites().observeAsState(emptyList())

    // Initialize a map to store the isFavourite state for each favorite item
    val isFavouriteMap = remember { mutableStateMapOf<String, Boolean>() }

    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Favourite page")
                Button(onClick = {
                    navController.navigate("FavouriteAnimatedView")
                }) {
                    Text(text = "FavouriteAnimatedView")
                }
                // Display favorite items as cards
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    /*items(favouriteItems.value ?: emptyList()) { favouriteItem ->
                        FavouriteItemCard(
                            favouriteItem = favouriteItem,
                        )
                    }*/
                    items(favouriteItems) { favouriteItem ->
                        val isFavouriteState = isFavouriteMap[favouriteItem.id] ?: false

                        FavouriteItemCard(
                            favouriteItem = favouriteItem,
                            //isFavourite = isFavouriteState,
                            onFavouriteStateChanged = { newFavouriteState ->
                                // Update the isFavouriteMap
                                isFavouriteMap[favouriteItem.id] = newFavouriteState

                                // Update the favorite state in the database using the ViewModel
                                val updatedFavouriteItem = favouriteItem.copy(isFavourite = newFavouriteState)
                                if (newFavouriteState) {
                                    favouriteViewModel.saveFavoriteItem(updatedFavouriteItem)
                                    Log.d("DBG", "Marked item ${updatedFavouriteItem.id} as a favourite")
                                } else {
                                    favouriteViewModel.deleteFavoriteItem(updatedFavouriteItem)
                                    Log.d("DBG", "Removed item ${updatedFavouriteItem.id} from favourites")
                                }
                            },
                            favouriteViewModel = favouriteViewModel

                        )
                    }
                }
            }
        }
        composable("FavouriteAnimatedView") {
            FavouriteAnimatedView()
        }
    }
}