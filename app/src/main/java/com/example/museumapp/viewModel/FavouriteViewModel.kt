package com.example.museumapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.museumapp.room.FavouriteDatabase
import com.example.museumapp.room.FavouriteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val db = FavouriteDatabase.getInstance(application)
    private val favouriteDao = db.favouriteDao

    val favouriteItems: LiveData<List<FavouriteItem>> = favouriteDao.getAllFavouriteItemsLiveData()
    //private val _favouriteItems = favouriteDao.getAllFavouriteItemsLiveData()
    //val favoriteItemsList = favouriteItems.value ?: emptyList()

    //private val _favouriteItems = MutableLiveData<List<FavouriteItem>>()
    // Expose the LiveData to observe changes
    //val favouriteItems: LiveData<List<FavouriteItem>> get() = _favouriteItems

    // Function to check if an item is a favorite by ID
    /*fun isFavourite(itemId: String): Boolean {
        val items = favouriteItems.value
        return items?.any { it.id == itemId } == true
        //return favouriteDao.isItemFavourite(itemId)
    }*/

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


//private val _favouriteItems = MutableLiveData<Set<String>>()
//val favouriteItemsLiveData: LiveData<Set<String>> = _favouriteItems

val isItemFavouriteLiveData: LiveData<Boolean> = MutableLiveData()


/*fun isFavourite(itemId: String) {
       viewModelScope.launch {
           val isFavourite = withContext(Dispatchers.IO) {
               favouriteDao.isItemFavourite(itemId)
           }
           // Update the LiveData with the result
           (isItemFavouriteLiveData as MutableLiveData).postValue(isFavourite)
       }
   }*/