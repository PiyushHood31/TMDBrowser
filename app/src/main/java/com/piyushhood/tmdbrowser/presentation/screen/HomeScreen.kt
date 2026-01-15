package com.piyushhood.tmdbrowser.presentation.screen


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.piyushhood.tmdbrowser.BuildConfig
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.presentation.component.MovieListItem
import com.piyushhood.tmdbrowser.presentation.state.UiState
import com.piyushhood.tmdbrowser.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel : HomeViewModel,
    onMovieClick : (Int) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()

    when(uiState){
        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text((uiState as UiState.Error).message)
            }
        }
        UiState.Loading ->{
            Log.d("TMDB_KEY", BuildConfig.TMDB_API_KEY)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is UiState.Success<List<Movie>> -> {
            Log.d("TMDB_KEY", BuildConfig.TMDB_API_KEY)
            val movies = (uiState as UiState.Success).data
            LazyColumn {
                items(movies){movie ->
                    MovieListItem(movie = movie,
                        onClick = {onMovieClick(movie.id.value)})
                }
            }

        }
    }

}