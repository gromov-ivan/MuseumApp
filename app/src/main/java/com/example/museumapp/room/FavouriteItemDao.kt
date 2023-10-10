package com.example.museumapp.room


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteItem(item: FavouriteItem)

//    @Update
//    suspend fun updateFavouriteItem(item: FavouriteItem)

    @Delete
    suspend fun deleteFavouriteItem(item: FavouriteItem)

    @Query("SELECT * FROM favourite_items")
    fun getAllFavouriteItems(): Flow<List<FavouriteItem>>
}
