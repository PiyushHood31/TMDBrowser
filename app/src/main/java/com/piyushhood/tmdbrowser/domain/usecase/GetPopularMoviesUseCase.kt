package com.piyushhood.tmdbrowser.domain.usecase

import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
){
    suspend operator fun invoke(
        page : Int,
        language : String
    ) : List<Movie> {
        return repository.getPopularMovies(
            page = page,
            language = language
        )
    }
}