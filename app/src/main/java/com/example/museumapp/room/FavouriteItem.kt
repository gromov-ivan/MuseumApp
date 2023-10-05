package com.example.museumapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_items")
data class FavouriteItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val itemId: String,
    val itemName: String
)
