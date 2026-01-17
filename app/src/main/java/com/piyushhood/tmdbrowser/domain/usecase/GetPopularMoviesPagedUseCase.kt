package com.piyushhood.tmdbrowser.domain.usecase

import androidx.paging.PagingData
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesPagedUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(
        language : String
    ) : Flow<PagingData<Movie>>{
        return repository.getPopularMoviesPaged(language)
    }

    suspend fun refresh(language: String){
        repository.refreshPopularMovies(language)
    }
}