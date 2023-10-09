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

    val favouriteItems: LiveData<List<FavouriteItem>> = favouriteDao.getAllFavouriteItemsLiveData()

    // Function to insert a new favorite item
    fun saveFavoriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            favouriteItem.isFavourite = true
            favouriteDao.insertFavouriteItem(favouriteItem)
        }
    }

    // Function to delete a favorite item
    fun deleteFavoriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            favouriteDao.deleteFavouriteItem(favouriteItem)
        }
    }

    // Function to update a favorite item (e.g., toggling the favorite status)
    fun updateFavoriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            favouriteDao.updateFavouriteItem(favouriteItem)
        }
    }

    fun getAllFavourites(): LiveData<List<FavouriteItem>> {
        return db.favouriteDao.getAllFavouriteItemsLiveData()
    }

}