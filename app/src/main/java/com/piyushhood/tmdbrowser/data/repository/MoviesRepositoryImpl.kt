package com.piyushhood.tmdbrowser.data.repository

import com.piyushhood.tmdbrowser.BuildConfig
import com.piyushhood.tmdbrowser.data.mapper.toDomain
import com.piyushhood.tmdbrowser.data.remote.api.TmdbService
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val apiService : TmdbService
) : MovieRepository {
    override suspend fun getPopularMovies(
        page: Int,
        language: String
    ): List<Movie> = withContext(Dispatchers.IO) {
        val response = apiService.getPopularMovies(
            language = language,
            page =  page
        )

        response.results.toDomain()

    }

    override suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): Movie  = withContext(Dispatchers.IO){
        apiService.getMovieDetails(
            movieId = movieId,
            language = language
        ).toDomain()

    }


}