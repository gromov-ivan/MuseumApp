package com.example.museumapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.museumapp.room.FavouriteDatabase
import com.example.museumapp.room.FavouriteItem

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val db = FavouriteDatabase.getInstance(application)

    fun getAllFavourites(): LiveData<List<FavouriteItem>> {
        return db.favouriteDao.getAllFavouriteItemsLiveData()
    }

}