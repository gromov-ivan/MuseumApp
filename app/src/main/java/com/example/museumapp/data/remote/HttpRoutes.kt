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

    //const val RECORD_ENDPOINT = "$BASE_URL/records.json"
    //const val MUSEUM_ENDPOINT = "$BASE_URL/TuusulaMuseum.json"



    private const val BASE_URL_JSON = "https://jsonplaceholder.typicode.com"
    const val POSTS = "$BASE_URL_JSON/posts"

    private const val BASE_URL_FINNA = "https://api.finna.fi/v1"
    const val SEARCH_ENDPOINT = "$BASE_URL_FINNA/search"

    fun getRecordsUrl(): String {
        return "$SEARCH_ENDPOINT" +
                "?sort=main_date_str+asc,id+asc&view=grid" +
                "&filter[]=building:0/Tuusulan+museo/" +
                "&filter[]=usage_rights_ext_str_mv:0/B+BY/" +
                "&filter[]=format_ext_str_mv:0/WorkOfArt/"
        // Organization: Tuusula museum
        // Usage rights // Free, name the source
        // Work of art -> Drawing, Painting, Graphics
    }

}