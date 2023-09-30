package com.example.museumapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.composable.CameraView
import com.example.museumapp.composable.CollectionDetailView
import com.example.museumapp.composable.CollectionList
import com.example.museumapp.composable.CollectionsCard
import com.example.museumapp.composable.FauvoritesView
import com.example.museumapp.composable.HomePage
import com.example.museumapp.composable.NavigationItem
import com.example.museumapp.data.remote.dto.MuseumItem

@Composable
fun NavigationController(navController: NavHostController, viewModel: MuseumViewModel, selectedCard: String) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
            HomePage(navController)
        }

        composable("collectionsCard") {
            CollectionsCard(navController, viewModel)
        }

        composable("collectionList") {
            val museumData by viewModel.museumData.observeAsState(emptyList())
            CollectionList(museumData, viewModel, selectedCard = selectedCard)
        }

        composable("collectionDetailView") {
            CollectionDetailView()
        }

        composable(NavigationItem.Camera.route) {
            CameraView()
        }

        composable(NavigationItem.Favourite.route) {
            FauvoritesView()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(viewModel: MuseumViewModel) {

    val navController = rememberNavController()
    var selectedCard by remember { mutableStateOf("Tuusula Museum") }

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Camera,
        NavigationItem.Favourite
    )

    // fix tabs
    LaunchedEffect(selectedCard) {
        // Define the tabs based on the selected card
        val tabs = when (selectedCard) {
            "Tuusula Museum" -> listOf("Drawings", "Pictures")
            "Ateneum Museum" -> listOf("Graphics", "Sculptures")
            "Photography Museum" -> listOf("Cities", "Agriculture")
            else -> emptyList()
        }

        // Set the updated tabs
        //tabs.clear()
        // tabs.addAll(updatedTabs)
    }

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

                                navController.graph?.startDestinationRoute?.let {
                                    navController.popBackStack(it,true)
                                }

                                navController.navigate(it.route){
                                    launchSingleTop = true
                                }
                            }
                        })
                }
            }
        }) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavigationController(navController = navController, viewModel = viewModel, selectedCard = selectedCard)
        }
    }
}