package com.lukman.jetpackfinal.data.source.remote

import com.lukman.jetpackfinal.data.repository.BaseRepository
import com.lukman.jetpackfinal.data.source.remote.network.ApiServices
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository() {

    suspend fun getNowPlayingMovie() = safeApiCall { apiServices.getNowPlayingMovies() }

}