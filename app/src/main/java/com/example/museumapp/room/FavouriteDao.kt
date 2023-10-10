package com.example.museumapp.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteItem(item: FavouriteItem)

    @Update
    suspend fun updateFavouriteItem(item: FavouriteItem)

    @Delete
    suspend fun deleteFavouriteItem(item: FavouriteItem)

    @Query("SELECT * FROM FavouriteItem")
    fun getAllFavouriteItemsLiveData(): LiveData<List<FavouriteItem>>

}
