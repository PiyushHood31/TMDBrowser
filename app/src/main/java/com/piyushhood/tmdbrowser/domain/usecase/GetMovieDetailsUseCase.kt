package com.piyushhood.tmdbrowser.domain.usecase

import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
){
    suspend operator fun invoke(
        movieId : Int,
        language : String
    ) : Movie {
        return repository.getMovieDetails(movieId ,language)
    }
}