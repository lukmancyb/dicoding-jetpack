package com.lukman.jetpackfinal.data.source.remote.network

import com.lukman.jetpackfinal.data.source.remote.response.MovieResponse
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiServices: ApiServices
) : ApiHelper{
    override suspend fun getMovies(): MovieResponse = apiServices.getNowPlayingMovies()
}