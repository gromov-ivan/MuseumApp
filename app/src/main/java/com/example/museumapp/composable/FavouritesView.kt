package com.example.museumapp.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.museumapp.viewModel.FavouriteViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouritesView(favouriteViewModel: FavouriteViewModel) {
    val navController = rememberNavController()

    // Observe the LiveData
    val favouriteItems by favouriteViewModel.getAllFavourites().observeAsState(emptyList())

    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            Box {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(favouriteItems) { favouriteItem ->
                        FavouriteItemCard(
                            favouriteItem = favouriteItem,
                            navController = navController,
                            favouriteViewModel = favouriteViewModel
                        )
                    }
                }
                // Add FloatingActionButton for navigation
                FloatingActionButton(
                    onClick = {
                        navController.navigate("FavouriteAnimatedView")
                    },
                    modifier = Modifier
                        .padding(16.dp) // Add appropriate padding
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(imageVector = Icons.Default.Animation, contentDescription = "Navigate")
                }
            }
        }
        composable("FavouriteAnimatedView") {
            FavouriteAnimatedView(favouriteViewModel)
        }
        composable(
            route = "favouriteDetailView/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")

            if (itemId != null) {
                FavouriteDetailView(itemId, favouriteViewModel)
            } else {
                Text(text = "Item not found", fontSize = 20.sp)
            }
        }
    }
}