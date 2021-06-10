package com.lukman.jetpackfinal.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.lukman.jetpackfinal.data.repository.MovieRepository
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    init {
        loadMovies()
    }

    private lateinit var _movieData: LiveData<Resource<PagedList<MovieEntity>>>
    val movieData get() = _movieData

    fun loadMovies() {
        viewModelScope.launch {
            _movieData = repo.getNowPlayingMovies()
        }

    }

}