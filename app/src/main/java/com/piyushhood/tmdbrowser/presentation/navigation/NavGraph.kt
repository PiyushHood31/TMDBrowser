package com.piyushhood.tmdbrowser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.piyushhood.tmdbrowser.presentation.screen.HomeScreen
import com.piyushhood.tmdbrowser.presentation.screen.MovieDetailScreen
import com.piyushhood.tmdbrowser.presentation.screen.SearchScreen
import com.piyushhood.tmdbrowser.presentation.screen.SettingsScreen
import com.piyushhood.tmdbrowser.presentation.viewmodel.HomeViewModel
import com.piyushhood.tmdbrowser.presentation.viewmodel.MovieDetailsViewModel
import com.piyushhood.tmdbrowser.presentation.viewmodel.PreferencesViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    preferencesViewModel: PreferencesViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val homeViewModel : HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = homeViewModel,
                onMovieClick = { movieId ->
                    navController.navigate(
                        Screen.MovieDetails.createRoute(movieId)
                    )
                }
            )


        }

        composable(

            Screen.MovieDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {backStackEntry ->

            val movieDetailsViewModel : MovieDetailsViewModel = hiltViewModel(backStackEntry)
            MovieDetailScreen(
                viewModel = movieDetailsViewModel

            )
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