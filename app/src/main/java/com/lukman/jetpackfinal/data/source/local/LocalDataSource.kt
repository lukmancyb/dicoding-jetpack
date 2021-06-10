package com.lukman.jetpackfinal.data.source.local

import androidx.paging.DataSource
import com.lukman.jetpackfinal.data.source.local.database.MovieDao
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getPopularMovies()

    suspend fun insertMovies(movies : List<MovieEntity>) = movieDao.insertMovies(movies)
}