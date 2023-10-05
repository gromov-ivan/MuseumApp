package com.example.museumapp.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class MuseumItem(
    val id: String,
    val imageCopyright: String,
    val images: String,
    val imageDescription:String,
    val nonPresenterAuthorsName: String,
    val role: String,
    val subjects: List<List<String>>,
    val title: String,
    val year: String,
    val name: String

)

