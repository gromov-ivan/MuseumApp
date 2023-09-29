package com.example.museumapp.data.remote.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class MuseumItem(
    val id: String,
    val imageCopyright: String,
    val images: String,
    val imageDescription: String,
    val nonPresenterAuthorsName: String,
    val role: String,
    val subjects: List<List<String>>,
    val title: String,
    val year: String
)
@Serializable
data class PostResponse(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int
)

@Serializable
data class MuseumResponse(
    val resultCount: Int,
    val records: List<MuseumRecord>,
    val status: String
)

@Serializable
data class MuseumRecord(
    val buildings: List<Building>,
    val formats: List<Format>,
    val id: String,
    val imageRights: ImageRights,
    val images: List<String>,
    val languages: List<String>,
    val nonPresenterAuthors: List<Author>,
    val onlineUrls: List<String>,
    val presenters: Presenters,
    val rating: Rating,
    val series: List<Series>,
    val subjects: List<List<String>>,
    val title: String,
    val year: String
)
@Serializable
data class Building(
    val value: String,
    val translated: String
)
@Serializable
data class Format(
    val value: String,
    val translated: String
)
@Serializable
data class ImageRights(
    val copyright: String,
    val link: String,
    val description: List<String>
)
@Serializable
data class Author(
    val name: String,
    val role: String
)
@Serializable
data class Presenters(
    val presenters: Map<String, @Contextual Any>,
    val details: Map<String, @Contextual Any>
)
@Serializable
data class Rating(
    val count: Int,
    val average: Int
)
@Serializable
data class Series(
    val name: String
)

