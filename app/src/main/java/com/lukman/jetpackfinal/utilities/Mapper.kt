package com.lukman.jetpackfinal.utilities

import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity
import com.lukman.jetpackfinal.data.source.remote.response.Movie

object Mapper {

    fun movieResponseToEntities(movies: List<Movie>): List<MovieEntity> {

        val movieEntities = mutableListOf<MovieEntity>()
        for (movie in movies) {
            val entities = MovieEntity(
                id = movie.id,
                title = movie.title,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                posterPath = movie.posterPath,
                popularity = movie.popularity,
                releaseDate = movie.releaseDate,
                voteCount = movie.voteCount,
                voteAverage = movie.voteAverage,
                backdropPath = movie.backdropPath,
                originalLanguage = movie.originalLanguage
            )
            movieEntities.add(entities)

        }

        return movieEntities
    }
}