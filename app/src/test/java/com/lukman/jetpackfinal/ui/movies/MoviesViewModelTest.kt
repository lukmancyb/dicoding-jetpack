package com.lukman.jetpackfinal.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lukman.jetpackfinal.data.repository.MovieRepository
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest : TestCase() {


    private lateinit var moviesViewModel: MoviesViewModel

    @get:Rule
    var instantTaskExcecurtorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>


    @Before
    public override fun setUp() {
        moviesViewModel = MoviesViewModel(movieRepository)
    }

    @Test
    fun getMovieTest() {
        val dummy = Resource.Success(pagedList)
        Mockito.`when`(dummy.data?.size).thenReturn(1)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummy

        Mockito.`when`(movieRepository.getNowPlayingMovies()).thenReturn(movie)
        val movieEntities = moviesViewModel.loadMovies().value?.data
        verify(movieRepository).getNowPlayingMovies()
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        moviesViewModel.loadMovies().observeForever(observer)
        verify(observer).onChanged(dummy)


    }

}