package com.piyushhood.tmdbrowser.domain.repository

import androidx.paging.PagingData
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.model.MovieId
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(
        page : Int = 1,
        language : String
    ) : Flow<List<Movie>>

    fun getPopularMoviesPaged(
        language: String
    ):Flow<PagingData<Movie>>

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