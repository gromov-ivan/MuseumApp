package com.example.museumapp.room

import android.app.Application
import androidx.room.Room

class FavoriteList : Application() {

    companion object {
        lateinit var database: FavoriteDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize the Room database
        database = Room.databaseBuilder(
            this,
            FavoriteDatabase::class.java,
            "favorite_database"
        ).build()
    }
}