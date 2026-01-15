package com.piyushhood.tmdbrowser.domain.repository

import com.piyushhood.tmdbrowser.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(
        page : Int = 1,
        language : String
    ) : List<Movie>

    suspend fun getMovieDetails(
        movieId : Int,
        language : String
    ): Movie
}