package com.piyushhood.tmdbrowser.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.piyushhood.tmdbrowser.presentation.theme.Dimens

@Composable
fun HomeScreen(
    onMovieClick : () -> Unit
){
    Scaffold { padding ->
        Button(onClick = onMovieClick ,
            modifier = Modifier.padding(Dimens.Medium)) {
            Text("Go to Movie Details")
        }
    }
}