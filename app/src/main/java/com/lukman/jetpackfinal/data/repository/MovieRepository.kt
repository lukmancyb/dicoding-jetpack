package com.lukman.jetpackfinal.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lukman.jetpackfinal.data.MoviesDataSource
import com.lukman.jetpackfinal.data.source.local.LocalDataSource
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.data.source.remote.RemoteDataSource
import com.lukman.jetpackfinal.data.source.remote.network.ApiResponse
import com.lukman.jetpackfinal.data.source.remote.network.NetworkBoundResource
import com.lukman.jetpackfinal.data.source.remote.response.MovieResponse
import com.lukman.jetpackfinal.utilities.Mapper
import com.lukman.jetpackfinal.vo.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MoviesDataSource {

    override fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> {


        return object : NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(){
            override  fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean  = data.isNullOrEmpty()

            override suspend fun createCall(): LiveData<ApiResponse<MovieResponse>> = remoteDataSource.getNowPlayingMovie().asLiveData()

            override suspend fun saveCallResult(data: MovieResponse) {
                data.results?.let {
                    localDataSource.insertMovies(Mapper.movieResponseToEntities(it))

                }
            }

        }.toLiveData()


    }



}