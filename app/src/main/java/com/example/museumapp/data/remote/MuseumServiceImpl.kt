package com.example.museumapp.data.remote

import com.example.museumapp.data.remote.dto.MuseumItem
import com.example.museumapp.data.remote.dto.MuseumRecord
import com.example.museumapp.data.remote.dto.MuseumResponse
import com.example.museumapp.data.remote.dto.PostResponse
import com.google.gson.JsonParseException
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
            client.get {url(HttpRoutes.getRecordsUrl())}
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

    //override suspend fun getRecords(): List<PostResponse> {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
                client.get {url(HttpRoutes.POSTS)}
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

    override suspend fun getMuseum(): List<MuseumItem> {
        return try {
            client.get {url(HttpRoutes.MUSEUM_ENDPOINT)}
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
    /*
    val response = client.get<HttpResponse> {
                    url(HttpRoutes.RECORD_ENDPOINT)
                    // You can set additional options here if needed
                }

                if (response.status == HttpStatusCode.OK) {
                    val json = response.receive<String>()
                    val parsedData = Json.decodeFromString<List<MuseumRecord>>(json)
                    return parsedData
                } else {
                    // Handle non-OK status codes if needed
                    val errorResponse = response.readText()
                    println("Received non-OK status: ${response.status}")
                    println("Error response: $errorResponse")
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
                println("Error while fetching data: ${e.message}")
            }

            // Return an empty list or handle the error case as needed
            return emptyList()
            ---------
            //client.get { url(HttpRoutes.RECORD_ENDPOINT) }
            // http request
            /*val jsonString: String = client.get { url(HttpRoutes.getRecordsUrl()) }
            println("JSON String: $jsonString")

            // Check if the jsonString is null or empty before parsing
            if (jsonString.isNullOrEmpty()) {
                println("JSON String is empty or null.")
                return emptyList()
            } else {
                println("JSON String is NOT empty")
            }

            // Parse the JSON string into a list of MuseumResponse objects
            val museumResponses: List<MuseumResponse> = Json.decodeFromString(jsonString)

            museumResponses*/
     */
