package com.example.museumapp.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@ExperimentalFoundationApi
@Composable
fun FauvoritesView(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                }

            }
        }
        composable("FavouriteAnimatedView") {
            FavouriteAnimatedView()
        }
    }
}