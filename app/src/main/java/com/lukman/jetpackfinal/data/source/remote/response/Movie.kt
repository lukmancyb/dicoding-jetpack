package com.lukman.jetpackfinal.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean= false,
    @SerializedName("backdrop_path")
    val backdropPath: String  ="",
    val id: Int =-1,
    @SerializedName("original_language")
    val originalLanguage: String ="",
    @SerializedName("original_title")
    val originalTitle: String ="",
    val overview: String  ="",
    val popularity: Double =0.0,
    @SerializedName("poster_path")
    val posterPath: String ="",
    @SerializedName("release_date")
    val releaseDate: String ="",
    val title: String ="",
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double =0.0,
    @SerializedName("vote_count")
    val voteCount: Int 
)