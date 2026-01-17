package com.piyushhood.tmdbrowser.domain.repository

import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.model.MovieId
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(
        page : Int = 1,
        language : String
    ) : Flow<List<Movie>>

    suspend fun refreshPopularMovies(
        language: String
    )

    fun getMovieDetails(
        movieId : MovieId,
    ): Flow<Movie?>

    suspend fun refreshMovieDetails(
        movieId: MovieId,
        language: String
    )
}