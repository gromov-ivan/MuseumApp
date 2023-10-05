package com.example.museumapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoriteItemViewModel(private val repository: FavoriteItemDao) : ViewModel() {

    val favoriteItems: Flow<List<FavoriteItem>> = repository.getAllFavoriteItems()

    fun insertFavoriteItem(item: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavoriteItem(item)
        }
    }

    fun deleteFavoriteItem(item: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteItem(item)
        }
    }
}