package com.example.museumapp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.composable.CameraView
import com.example.museumapp.composable.CollectionDetailView
import com.example.museumapp.composable.CollectionList
import com.example.museumapp.composable.CollectionsCard
import com.example.museumapp.composable.FavouritesView
import com.example.museumapp.viewModel.FavouriteViewModel
import com.example.museumapp.viewModel.MuseumViewModel

@Composable
fun NavigationController(
    navController: NavHostController,
    viewModel: MuseumViewModel,
    favouriteViewModel: FavouriteViewModel
) {

    val selectedCard = remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
            CollectionsCard(navController, viewModel) { cardType ->
                selectedCard.value = cardType
            }
        }

        composable("collectionsCard") {
            CollectionsCard(navController, viewModel) { cardType ->
                selectedCard.value = cardType
            }
        }

        composable("collectionList") {
            val museumData by viewModel.museumData.observeAsState(emptyList())
            CollectionList(museumData, viewModel, selectedCard.value, navController, favouriteViewModel)
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
            FavouritesView(favouriteViewModel)
        }
    }
}

@Composable
fun Navigation(viewModel: MuseumViewModel, favouriteViewModel: FavouriteViewModel) {

    val navController = rememberNavController()

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Camera,
        NavigationItem.Favourite
    )

    Scaffold(
        bottomBar = {
            BottomNavigation (
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.drawWithContent {
                    drawContent()
                    drawLine(
                        color = Color(0xFFDFE2E7),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            ) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach {item ->
                    val isSelected = currentRoute == item.route
                    BottomNavigationItem(selected = isSelected,
                        label = {
                            Text(
                                text = item.label
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        onClick = {
                            if (!isSelected) {
                                navController.graph.startDestinationRoute?.let {
                                    navController.popBackStack(it, true)
                                }

                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavigationController(navController = navController, viewModel = viewModel, favouriteViewModel = favouriteViewModel)
        }
    }
}
