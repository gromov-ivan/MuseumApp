package com.example.museumapp.room


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow

class FavouriteItemRepository(private val dao: FavouriteItemDao) {

    val allFavouriteItems: LiveData<List<FavouriteItem>> = dao.getAllFavouriteItems().asLiveData()

    suspend fun insertFavouriteItem(item: FavouriteItem) {
        dao.insertFavouriteItem(item)
    }

    suspend fun deleteFavouriteItem(item: FavouriteItem) {
        dao.deleteFavouriteItem(item)
    }
}