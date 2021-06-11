package com.lukman.jetpackfinal.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.data.source.remote.network.ApiResponse
import com.lukman.jetpackfinal.data.source.remote.response.MovieResponse
import com.lukman.jetpackfinal.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {

     fun getNowPlayingMovies() : LiveData<Resource<PagedList<MovieEntity>>>

}