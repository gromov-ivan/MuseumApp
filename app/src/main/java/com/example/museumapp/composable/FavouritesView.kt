package com.example.museumapp.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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

    val favouriteItems by favouriteViewModel.getAllFavourites().observeAsState(emptyList())

    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            Column(modifier = Modifier.fillMaxSize()) {

                Text(
                    text = "Favorites",
                    modifier = Modifier.padding(20.dp, 24.dp, 20.dp, 2.dp),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Your curated collection, available anytime offline",
                    modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp),
                    fontSize = 16.sp,
                )


                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(favouriteItems) { favouriteItem ->
                            FavouriteItemCard(
                                favouriteItem = favouriteItem,
                                navController = navController,
                                favouriteViewModel = favouriteViewModel
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            navController.navigate("FavouriteAnimatedView")
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomEnd),
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = MaterialTheme.colorScheme.tertiary,
                        elevation = FloatingActionButtonDefaults.elevation(2.dp, 2.dp, 2.dp, 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Image,
                            contentDescription = "Navigate",
                            tint = MaterialTheme.colorScheme.onTertiary,
                        )
                    }
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