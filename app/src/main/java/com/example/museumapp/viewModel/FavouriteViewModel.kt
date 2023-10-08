package com.example.museumapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.museumapp.room.FavouriteDatabase
import com.example.museumapp.room.FavouriteItem
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val db = FavouriteDatabase.getInstance(application)
    private val favouriteDao = db.favouriteDao

    // Function to insert a new favorite item
    fun saveFavoriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            favouriteDao.insertFavouriteItem(favouriteItem)
        }
    }

    // Function to delete a favorite item
    fun deleteFavoriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            favouriteDao.deleteFavouriteItem(favouriteItem)
        }
    }

    fun getAllFavourites(): LiveData<List<FavouriteItem>> {
        return db.favouriteDao.getAllFavouriteItemsLiveData()
    }

}