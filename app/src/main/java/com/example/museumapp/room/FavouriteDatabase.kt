package com.example.museumapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteItem::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract val favouriteDao: FavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavouriteDatabase? = null
        fun getInstance(context: Context): FavouriteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        FavouriteDatabase::class.java, "favourite_database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}