package com.lukman.jetpackfinal.utilities

import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity

object DummyData {

    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val listMovie = ArrayList<MovieEntity>()
        listMovie.add(
            MovieEntity(
                id = 337404,
                backdropPath = "6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                originalLanguage = "en",
                originalTitle = "Cruella",
                overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                popularity = 6683.453,
                posterPath = "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                releaseDate = "2021-05-26",
                title = "Cruella",
                voteAverage = 8.7,
                voteCount = 2203,
                video = false,
                isFavorite = false
            )
        )
        return listMovie
    }


    fun generateRemoteDummyMovies(): ArrayList<MovieEntity> {
        val listMovie = ArrayList<MovieEntity>()
        listMovie.add(
            MovieEntity(
                id = 337404,
                backdropPath = "6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                originalLanguage = "en",
                originalTitle = "Cruella",
                overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                popularity = 6683.453,
                posterPath = "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
                releaseDate = "2021-05-26",
                title = "Cruella",
                voteAverage = 8.7,
                voteCount = 2203,
                video = false,
                isFavorite = false
            )
        )
        return listMovie
    }
}