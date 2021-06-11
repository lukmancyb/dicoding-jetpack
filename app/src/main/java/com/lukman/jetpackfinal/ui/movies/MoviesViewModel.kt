package com.lukman.jetpackfinal.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lukman.jetpackfinal.data.repository.MovieRepository
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    init {
        loadMovies()
    }

    fun loadMovies()  : LiveData<Resource<PagedList<MovieEntity>>> = repo.getNowPlayingMovies()

}