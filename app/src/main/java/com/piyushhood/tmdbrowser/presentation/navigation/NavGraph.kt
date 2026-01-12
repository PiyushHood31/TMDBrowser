package com.piyushhood.tmdbrowser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.piyushhood.tmdbrowser.presentation.screen.HomeScreen
import com.piyushhood.tmdbrowser.presentation.screen.MovieDetailScreen
import com.piyushhood.tmdbrowser.presentation.screen.SearchScreen
import com.piyushhood.tmdbrowser.presentation.screen.SettingsScreen
import com.piyushhood.tmdbrowser.presentation.viewmodel.PreferencesViewModel

@Composable
fun AppNavGraph(
    navController : NavHostController,
    preferencesViewModel : PreferencesViewModel,
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(Screen.Home.route){
            HomeScreen(
                onMovieClick = {
                    navController.navigate(Screen.MovieDetails.route)
                }
            )
        }

        composable(Screen.MovieDetails.route){
            MovieDetailScreen()
        }

        composable(Screen.Search.route) {
            SearchScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                preferencesViewModel = preferencesViewModel
            )
        }
    }
}