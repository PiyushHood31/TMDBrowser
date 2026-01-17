package com.piyushhood.tmdbrowser.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.piyushhood.tmdbrowser.presentation.component.EmptyState
import com.piyushhood.tmdbrowser.presentation.component.MovieListItem
import com.piyushhood.tmdbrowser.presentation.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel : HomeViewModel,
    onMovieClick : (Int) -> Unit
){
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) {
        EmptyState()
    } else {
        LazyColumn {
            items(movies){movie ->
                MovieListItem(movie = movie,
                    onClick = {onMovieClick(movie.id.value)})
            }
        }
    }

}