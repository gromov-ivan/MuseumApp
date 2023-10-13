package com.example.museumapp.data.remote

object HttpRoutes {

    private const val BASE_URL_TUUSULA = "https://users.metropolia.fi/~ainaral/json/TuusulaMuseum"
    private const val BASE_URL_PHOTOGRAPHY = "https://users.metropolia.fi/~ainaral/json/PhotographicArtMuseum"
    private const val BASE_URL_ATENEUM = "https://users.metropolia.fi/~ainaral/json/AteneumArtMuseum"

    const val DRAWINGS_ENDPOINT = "$BASE_URL_TUUSULA/drawings.json"
    const val PICTURES_ENDPOINT = "$BASE_URL_TUUSULA/pictures.json"

    const val AGRICULTURE_ENDPOINT = "$BASE_URL_PHOTOGRAPHY/agriculture.json"
    const val CITIES_ENDPOINT = "$BASE_URL_PHOTOGRAPHY/cities.json"

    const val GRAPHICS_ENDPOINT = "$BASE_URL_ATENEUM/graphics.json"
    const val SCULPTURE_ENDPOINT = "$BASE_URL_ATENEUM/sculpture.json"

}