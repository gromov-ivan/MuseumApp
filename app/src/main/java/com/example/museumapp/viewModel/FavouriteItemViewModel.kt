package com.example.museumapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.museumapp.room.FavouriteDatabase
import com.example.museumapp.room.FavouriteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
class FavouriteItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavouriteItemRepository
    val allFavouriteItems: LiveData<List<FavouriteItem>>

    init {
        val favouriteItemDao = FavouriteDatabase.getInstance(application).favouriteDao()
        repository = FavouriteItemRepository(favouriteItemDao)
        allFavouriteItems = repository.allFavouriteItems
    }



    fun insertFavouriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavouriteItem(favouriteItem)
        }
    }

    fun deleteFavouriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavouriteItem(favouriteItem)
        }
    }
}*/