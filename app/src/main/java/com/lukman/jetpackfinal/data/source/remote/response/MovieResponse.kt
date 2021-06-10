package com.lukman.jetpackfinal.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int?,
    val results: List<Movie>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)