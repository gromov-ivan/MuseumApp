package com.example.museumapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem (
    val route: String,
    val label:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {

    object Home: NavigationItem(route = "collectionsCard", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Camera: NavigationItem(route = "camera", "Scanner", Icons.Filled.QrCodeScanner, Icons.Outlined.QrCodeScanner)
    object Favourite: NavigationItem(route = "favourite", "Favorites", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)

}
