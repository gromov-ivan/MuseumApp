package com.example.museumapp.data.remote

import com.example.museumapp.data.remote.dto.MuseumItem
import com.example.museumapp.data.remote.dto.MuseumRecord
import com.example.museumapp.data.remote.dto.MuseumResponse
import com.example.museumapp.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.http.ContentType.Application.Json

interface MuseumService {

    suspend fun getMuseumArt(): List<MuseumItem>

    companion object {
        fun create(): MuseumService {
            return MuseumServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer= KotlinxSerializer()
                    }
                }
            )
        }
    }
}