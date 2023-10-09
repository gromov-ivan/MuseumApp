package com.example.museumapp.room

import android.app.Application
import androidx.room.Room

class FavouriteList : Application() {

    companion object {
        lateinit var database: FavouriteDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize the Room database
        database = Room.databaseBuilder(
            this,
            FavouriteDatabase::class.java,
            "favourite_database"
        ).build()
    }
}