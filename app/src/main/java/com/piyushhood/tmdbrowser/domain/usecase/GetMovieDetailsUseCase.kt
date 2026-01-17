package com.piyushhood.tmdbrowser.domain.usecase

import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.model.MovieId
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
){
     operator fun invoke(
        movieId : MovieId,
    ) : Flow<Movie?> {
        return repository.getMovieDetails(movieId)
    }

    suspend fun refresh(
        movieId : MovieId,
        language : String
    ){
        repository.refreshMovieDetails(movieId , language)
    }
}