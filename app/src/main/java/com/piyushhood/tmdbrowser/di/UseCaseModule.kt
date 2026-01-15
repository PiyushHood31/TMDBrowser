package com.piyushhood.tmdbrowser.di

import com.piyushhood.tmdbrowser.domain.repository.MovieRepository
import com.piyushhood.tmdbrowser.domain.usecase.GetMovieDetailsUseCase
import com.piyushhood.tmdbrowser.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(
        repository: MovieRepository
    ) : GetPopularMoviesUseCase{
        return GetPopularMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(
        repository: MovieRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}