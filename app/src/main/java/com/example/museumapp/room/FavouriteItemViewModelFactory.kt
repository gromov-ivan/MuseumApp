package com.example.museumapp.room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.museumapp.viewModel.FavouriteItemViewModel

class FavouriteItemViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouriteItemViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
