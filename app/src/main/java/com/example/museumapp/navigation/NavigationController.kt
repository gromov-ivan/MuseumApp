package com.example.museumapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.museumapp.viewModel.MuseumViewModel
import com.example.museumapp.composable.CameraView
import com.example.museumapp.composable.CollectionDetailView
import com.example.museumapp.composable.CollectionList
import com.example.museumapp.composable.CollectionsCard
import com.example.museumapp.composable.FavouritesView
import com.example.museumapp.composable.HomePage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationController(
    navController: NavHostController,
    viewModel: MuseumViewModel
) {

    val selectedCard = remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
            HomePage(navController)
        }

        composable("collectionsCard") {
            CollectionsCard(navController, viewModel){ cardType ->
                selectedCard.value = cardType
                navController.navigate("collectionList")
            }
        }

        composable("collectionList") {
            val museumData by viewModel.museumData.observeAsState(emptyList())
            CollectionList(museumData, viewModel, selectedCard.value, navController)
        }

        composable("collectionDetailView/{itemId}") {backStackEntry ->
            // Extract the item ID from the navigation arguments
            val itemId = backStackEntry.arguments?.getString("itemId")
            val museumData by viewModel.museumData.observeAsState(emptyList())
            // Retrieve the corresponding MuseumItem based on the item ID
            val selectedItem = museumData.find { it.id == itemId }

            if (selectedItem != null) {
                // Pass the selected item to the CollectionDetailView composable
                CollectionDetailView(selectedItem)
            } else {
                // Handle the case where the item is not found
                Text(text = "Item not found", fontSize = 20.sp)
            }
        }

        composable(NavigationItem.Camera.route) {
            CameraView()
        }

        composable(NavigationItem.Favourite.route) {
            FavouritesView()
        }
    }
}

@Composable
fun Navigation(viewModel: MuseumViewModel, navController: NavHostController) {

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Camera,
        NavigationItem.Favourite
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach {
                    BottomNavigationItem(selected = currentRoute == it.route,
                        label = {
                            Text(
                                text = it.label,
                                //color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = it.icon, contentDescription = null,
                                //tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        onClick = {
                            if(currentRoute!=it.route){

                                navController.graph.startDestinationRoute?.let {
                                    navController.popBackStack(it,true)
                                }

                                navController.navigate(it.route){
                                    launchSingleTop = true
                                }
                            }
                        })
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavigationController(navController = navController, viewModel = viewModel)
        }
    }
}