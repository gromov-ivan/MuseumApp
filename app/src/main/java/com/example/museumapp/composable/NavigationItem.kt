package com.example.museumapp.composable

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem (
    val route: String,
    val label:String,
    val icon: ImageVector) {

    object Home: NavigationItem(route = "home", "Home", Icons.Outlined.Home)
    object Camera: NavigationItem(route = "camera", "Camera", Icons.Outlined.CameraAlt)
    object Favourite: NavigationItem(route = "favourite", "Favourite", Icons.Outlined.FavoriteBorder)

}