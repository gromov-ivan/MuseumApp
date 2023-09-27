package com.example.museumapp.data.remote

import com.example.museumapp.data.remote.dto.MuseumResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface MuseumService {
    suspend fun getRecords(): List<MuseumResponse>
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