package com.piyushhood.tmdbrowser.presentation.viewmodel

import android.widget.ViewSwitcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piyushhood.tmdbrowser.BuildConfig
import com.piyushhood.tmdbrowser.data.repository.MoviesRepositoryImpl
import com.piyushhood.tmdbrowser.di.ServiceLocator
import com.piyushhood.tmdbrowser.domain.usecase.GetPopularMoviesUseCase

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = MoviesRepositoryImpl(
            apiService = ServiceLocator.tmdbService
        )

        val useCase = GetPopularMoviesUseCase(repository)

        return HomeViewModel(useCase) as T
    }

}