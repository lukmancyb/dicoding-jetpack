package com.lukman.jetpackfinal.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.lukman.jetpackfinal.FakeMovieRepository
import com.lukman.jetpackfinal.data.source.local.LocalDataSource
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.data.source.remote.RemoteDataSource
import com.lukman.jetpackfinal.utilities.DummyData
import com.lukman.jetpackfinal.utils.PagedListUtil
import com.lukman.jetpackfinal.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class MovieRepositoryTest {


    @get:Rule
    var instantTaskExcecutorRule = InstantTaskExecutorRule()


    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)

    private val movieRepository = FakeMovieRepository(remoteDataSource, localDataSource)

    private val movieResponse = DummyData.generateRemoteDummyMovies()


//    @Test
//    fun getMovies() {
//        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
//        Mockito.`when`(localDataSource.getMovies()).thenReturn(dataSourceFactory)
//        movieRepository.getNowPlayingMovies()
//
//        val movieEntities = Resource.Success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()))
//        verify(localDataSource).getMovies()
//        Assert.assertNotNull(movieEntities.data)
//        Assert.assertEquals(movieResponse.size.toLong(), movieEntities.data.size.toLong())
//
//    }

    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(localDataSource.getMovies()).thenReturn(dataSourceFactory)
        movieRepository.getNowPlayingMovies()

        val movieEntities =
            Resource.Success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()))
        verify(localDataSource).getMovies()
        Assert.assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size, movieEntities.data.size)
    }


}