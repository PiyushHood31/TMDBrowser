package com.piyushhood.tmdbrowser.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailScreen(){
    Scaffold {paddingValues ->

        Text("Movie Details Screen" ,
            modifier = Modifier.padding(paddingValues))
    }
}