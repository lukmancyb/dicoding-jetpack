package com.lukman.jetpackfinal.data.source.remote.network

import com.lukman.jetpackfinal.data.source.remote.response.MovieResponse
import retrofit2.http.GET

interface ApiServices {


    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : MovieResponse
}