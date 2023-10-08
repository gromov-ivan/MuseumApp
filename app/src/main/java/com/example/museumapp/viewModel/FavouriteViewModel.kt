package com.example.museumapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.museumapp.room.FavouriteDatabase
import com.example.museumapp.room.FavouriteItem
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val db = FavouriteDatabase.getInstance(application)
    private val favouriteDao = db.favouriteDao
    val favouriteItems = favouriteDao.getAllFavouriteItemsLiveData()

    // Function to check if an item is a favorite by ID
    fun isFavourite(itemId: String): Boolean {
        val items = favouriteItems.value
        return items?.any { it.id == itemId } == true
    }

    // Function to insert a new favorite item
    fun saveFavoriteItem(favouriteItem: FavouriteItem) {
        viewModelScope.launch {
            favouriteItem.isFavorite = true
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