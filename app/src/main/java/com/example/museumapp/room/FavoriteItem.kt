package com.example.museumapp.room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_items")
data class FavoriteItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val itemId: Int,
    val itemName: String
)
