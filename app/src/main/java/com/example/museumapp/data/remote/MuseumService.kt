package com.example.museumapp.data.remote

import com.example.museumapp.data.remote.dto.MuseumItem
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface MuseumService {

    suspend fun getTuusulaDrawings(): List<MuseumItem>
    suspend fun getTuusulaPitures(): List<MuseumItem>

    suspend fun getAteneumGraphics(): List<MuseumItem>
    suspend fun getAteneumSculpture(): List<MuseumItem>

    suspend fun getAgriculturePhotography(): List<MuseumItem>
    suspend fun getCitiesPhotography(): List<MuseumItem>

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