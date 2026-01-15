package com.piyushhood.tmdbrowser.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.usecase.GetMovieDetailsUseCase
import com.piyushhood.tmdbrowser.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel(){

    private val movieId: Int =
        savedStateHandle["movieId"]
            ?: throw IllegalStateException("movieId not found in SavedStateHandle")
    private val _uiState = MutableStateFlow<UiState<Movie>>(UiState.Loading)
    val uiState : StateFlow<UiState<Movie>> = _uiState

    init{
        loadMovie()
    }

    private fun loadMovie(){
        viewModelScope.launch {
            try{
                val movie = getMovieDetailsUseCase(
                    movieId = movieId,
                    language = "en"
                )
                _uiState.value = UiState.Success(movie)
            }catch( e : Exception){
                _uiState.value = UiState.Error(
                    e.localizedMessage ?: "Failed to load movie"
                )
            }
        }
    }
}