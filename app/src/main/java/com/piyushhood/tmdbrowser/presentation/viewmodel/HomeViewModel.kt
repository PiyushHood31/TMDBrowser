package com.piyushhood.tmdbrowser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.usecase.GetPopularMoviesPagedUseCase
import com.piyushhood.tmdbrowser.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularMoviesPagedUseCase: GetPopularMoviesPagedUseCase
)  : ViewModel(){

    val movies : StateFlow<List<Movie>> =
        getPopularMoviesUseCase(language = "en")
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    val pagedMovies : Flow<PagingData<Movie>> =
        getPopularMoviesPagedUseCase(language = "en")
            .cachedIn(viewModelScope)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing : StateFlow<Boolean> = _isRefreshing

    init {
        refresh()
    }

     fun refresh(){
        viewModelScope.launch {
            try {
                getPopularMoviesPagedUseCase.refresh(language = "en")
            }catch (_: Exception){
                //Offline- First -> ignore network errors
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}
