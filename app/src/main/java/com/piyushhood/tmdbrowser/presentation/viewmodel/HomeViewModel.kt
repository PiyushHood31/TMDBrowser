package com.piyushhood.tmdbrowser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.usecase.GetPopularMoviesUseCase
import com.piyushhood.tmdbrowser.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
)  : ViewModel(){
    private val _uiState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val uiState : StateFlow<UiState<List<Movie>>> = _uiState

    init{
        loadPopularMovies()
    }

    private fun loadPopularMovies (){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try{
                val movies = getPopularMoviesUseCase(
                    page = 1,
                    language = "en"
                )
                _uiState.value = UiState.Success(movies)
            } catch (e : Exception){
                _uiState.value = UiState.Error(
                    message = e.localizedMessage ?: "Something went wrong"
                )
            }
        }
    }

}