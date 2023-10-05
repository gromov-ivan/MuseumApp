package com.example.museumapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(item: FavoriteItem)

    @Update
    suspend fun updateFavoriteItem(item: FavoriteItem)

    @Delete
    suspend fun deleteFavoriteItem(item: FavoriteItem)

    @Query("SELECT * FROM favorite_items")
    fun getAllFavoriteItems(): Flow<List<FavoriteItem>>
}
