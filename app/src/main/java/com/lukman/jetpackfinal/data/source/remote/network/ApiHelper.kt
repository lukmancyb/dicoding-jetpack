package com.lukman.jetpackfinal.data.source.remote.network

import com.lukman.jetpackfinal.data.source.remote.response.MovieResponse

interface ApiHelper {

    suspend fun getMovies() : MovieResponse
}