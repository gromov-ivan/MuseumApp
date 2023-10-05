package com.example.museumapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteItem::class], version = 1)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favouriteItemDao(): FavouriteItemDao

    companion object {
        @Volatile
        private var INSTANCE: FavouriteDatabase? = null

        fun getInstance(context: Context): FavouriteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteDatabase::class.java,
                    "favourite_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}