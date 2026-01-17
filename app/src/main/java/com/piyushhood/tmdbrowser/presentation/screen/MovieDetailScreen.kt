package com.piyushhood.tmdbrowser.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piyushhood.tmdbrowser.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailScreen(
    viewModel : MovieDetailsViewModel
){
    val movie by viewModel.movie.collectAsState()

    if(movie == null){
       CircularProgressIndicator()
        Text(text = "It may take some time")
    }else{
        Column {
            Text(
                text = movie!!.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(8.dp))
            Text(movie!!.overview)
            Spacer(Modifier.height(8.dp))
            Text("Rating : ${movie!!.rating}")
        }
    }
}