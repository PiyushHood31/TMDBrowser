package com.piyushhood.tmdbrowser.data.repository

import com.piyushhood.tmdbrowser.BuildConfig
import com.piyushhood.tmdbrowser.data.local.dao.MovieDao
import com.piyushhood.tmdbrowser.data.local.entity.toDomain
import com.piyushhood.tmdbrowser.data.local.entity.toEntity
import com.piyushhood.tmdbrowser.data.mapper.toDomain
import com.piyushhood.tmdbrowser.data.remote.api.TmdbService
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.model.MovieId
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService : TmdbService,
    private val movieDao : MovieDao
 ) : MovieRepository {
    override  fun getPopularMovies(
        page: Int,
        language: String
    ): Flow<List<Movie>> {
        return movieDao.getMovies()
            .map { entities ->
                entities.map { it.toDomain() }
            }

    }

    override  fun getMovieDetails(
        movieId: MovieId,
    ): Flow<Movie?>{
        return movieDao.getMovieById(movieId =movieId.value )
            .map { it?.toDomain() }
    }

    override suspend fun refreshPopularMovies(language : String){
            val remoteMovies =
                apiService.getPopularMovies(language = language).results

            movieDao.clearMovies()
            movieDao.insetMovies(
                remoteMovies.map { it.toDomain().toEntity() }
            )

    }



    override suspend fun refreshMovieDetails(
        movieId: MovieId,
        language: String
    ){
            val movie =
                apiService.getMovieDetails(movieId = movieId.value , language = language)

            movieDao.insetMovies(
                listOf(movie.toDomain().toEntity())
            )
    }


}