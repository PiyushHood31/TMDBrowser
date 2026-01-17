package com.piyushhood.tmdbrowser.domain.usecase

import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
){
    operator fun invoke(
        language : String
    ) : Flow<List<Movie>> {
        return repository.getPopularMovies(
            language = language
        )
    }

    suspend fun refresh(language : String){
        repository.refreshPopularMovies(language)
    }
}