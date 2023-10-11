package com.example.museumapp.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun FavouritesView(favouriteViewModel: FavouriteViewModel){
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
                        .padding(16.dp)
                        .align(Alignment.BottomEnd)
                ) {
                   Icon(imageVector = Icons.Default.Animation, contentDescription = "Navigate" )
                }
            }
        }
        composable("FavouriteAnimatedView") {
            FavouriteAnimatedView(favouriteViewModel)
        }

        composable(
            route = "favouriteDetailView/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType})
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            if(itemId != null) {
                FavouriteDetailView(itemId, favouriteViewModel )
            } else {
                Text(text = "Item not Found", fontSize = 20.sp)
            }
        }
    }
}
   /* Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favourites") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(NavigationItem.FavouriteAnimated.route) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Favourite Animated"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(NavigationItem.FavouriteList.route) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Favourite List"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Favourite page")
            Button(onClick = {
                navController.navigate("FavouriteAnimatedView")
            }) {
                Text(text = "FavouriteAnimatedView")
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(favouriteItems) { favouriteItem ->
                    FavouriteItemCard(
                        favouriteItem = favouriteItem,
                        favouriteViewModel = favouriteViewModel
                    )
                }
            }
        }
    }
}*/