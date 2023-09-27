package com.example.museumapp.data.remote

import com.example.museumapp.data.remote.dto.MuseumRecord
import com.example.museumapp.data.remote.dto.MuseumResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
//import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MuseumServiceImpl(private val client: HttpClient): MuseumService {
    override suspend fun getRecords(): List<MuseumResponse> {
        return try {
            // http request
            val jsonString: String = client.get { url(HttpRoutes.getRecordsUrl()) }
            println("JSON String: $jsonString")

            listOf()

        } catch (e: RedirectResponseException) { // redirect issue
            // 3xx - responses
            println("Error redirect: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) { // post invalid data
            // 4xx - responses
            println("Error client: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) { // server side went wrong e.g database crashed
            // 5xx - responses
            println("Error server: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error error: ${e.message}")
            emptyList()
        }
    }
}
