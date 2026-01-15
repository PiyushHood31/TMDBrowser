package com.piyushhood.tmdbrowser.di

import com.piyushhood.tmdbrowser.data.repository.MoviesRepositoryImpl
import com.piyushhood.tmdbrowser.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        impl : MoviesRepositoryImpl
    ) : MovieRepository


}