package com.piyushhood.tmdbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.piyushhood.tmdbrowser.presentation.navigation.AppNavGraph
import com.piyushhood.tmdbrowser.presentation.scaffold.AdaptiveScaffold
import com.piyushhood.tmdbrowser.presentation.theme.TMDBrowserTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBrowserTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                AdaptiveScaffold(
                    windowSizeClass = windowSizeClass
                ) {modifier ->

                    AppNavGraph(navController = rememberNavController(),
                        modifier = modifier
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TMDBrowserTheme {

    }
}