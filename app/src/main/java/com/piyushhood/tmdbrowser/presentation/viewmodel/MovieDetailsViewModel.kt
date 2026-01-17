package com.piyushhood.tmdbrowser.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.model.MovieId
import com.piyushhood.tmdbrowser.domain.usecase.GetMovieDetailsUseCase
import com.piyushhood.tmdbrowser.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel(){

    private val movieId = MovieId(
        checkNotNull(savedStateHandle["movieId"])
    )

    val movie : StateFlow<Movie?> =
        getMovieDetailsUseCase(movieId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )

    init {
        refresh()
    }

    private fun refresh(){
        viewModelScope.launch {
            try{
                getMovieDetailsUseCase.refresh(
                    movieId = movieId,
                    language = "en"
                )
            } catch (_ : Exception){
                //Offline-first -> cached data still shown
            }
        }
    }
}