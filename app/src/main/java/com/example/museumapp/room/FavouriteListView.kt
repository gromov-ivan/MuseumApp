package com.example.museumapp.room

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.museumapp.viewModel.FavouriteItemViewModel




@Composable
fun FavouriteListView(favouriteViewModel: FavouriteItemViewModel) {
    val favouriteItems =favouriteViewModel.allFavouriteItems.value

    LazyColumn {
        items(favouriteItems.orEmpty()) { item ->
            Text(text = item.itemName)
        }
    }
}