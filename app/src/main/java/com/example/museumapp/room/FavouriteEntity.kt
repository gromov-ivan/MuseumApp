package com.example.museumapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteItem(
    @PrimaryKey (autoGenerate = false)
    val id: String,
    val images: String,
    val imageDescription: String,
    val nonPresenterAuthorsName: String,
    val title: String,
    val year: String,
    //val isFavorite: Boolean = false
)
