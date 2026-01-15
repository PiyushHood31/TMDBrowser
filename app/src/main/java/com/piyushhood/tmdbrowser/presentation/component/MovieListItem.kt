package com.piyushhood.tmdbrowser.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piyushhood.tmdbrowser.domain.model.Movie

@Composable
fun MovieListItem(
    movie: Movie,
    onClick : () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onClick()}
            .padding(16.dp)
    ){
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3
        )
    }
}