package com.example.museumapp.composable

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.navigation.NavigationItem
import com.example.museumapp.viewModel.FavouriteViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FavouritesView(favouriteViewModel: FavouriteViewModel, navController: NavController){
    val navController = rememberNavController()

    // Observe the LiveData
    val favouriteItems by favouriteViewModel.getAllFavourites().observeAsState(emptyList())

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
                    items(favouriteItems) { favouriteItem ->
                        FavouriteItemCard(
                            favouriteItem = favouriteItem,
                            favouriteViewModel = favouriteViewModel
                        )
                    }
                }
            }
        }
        composable("FavouriteAnimatedView") {
            FavouriteAnimatedView(favouriteItems = favouriteItems)
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