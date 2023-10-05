package com.example.museumapp.room


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteItemViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteItemViewModel(FavoriteList.database.favoriteItemDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}