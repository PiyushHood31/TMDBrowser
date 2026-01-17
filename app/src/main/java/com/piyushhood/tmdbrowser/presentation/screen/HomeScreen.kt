package com.piyushhood.tmdbrowser.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.piyushhood.tmdbrowser.presentation.component.EmptyState
import com.piyushhood.tmdbrowser.presentation.component.MovieListItem
import com.piyushhood.tmdbrowser.presentation.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState




@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMovieClick: (Int) -> Unit
) {
    val movies by viewModel.movies.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = isRefreshing
    )

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { viewModel.refresh() },
        modifier = Modifier.fillMaxSize()
    ) {
        if (movies.isEmpty() && !isRefreshing) {
            EmptyState()
        } else {
            LazyColumn {
                items(movies) { movie ->
                    MovieListItem(
                        movie = movie,
                        onClick = { onMovieClick(movie.id.value) }
                    )
                }
            }
        }
    }
}
