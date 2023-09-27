package com.example.museumapp.data.remote

object HttpRoutes {

    private const val BASE_URL = "https://api.finna.fi/v1"
    const val SEARCH_ENDPOINT = "$BASE_URL/search"

    fun getSearchUrl(): String {
        return "$SEARCH_ENDPOINT"
    }

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