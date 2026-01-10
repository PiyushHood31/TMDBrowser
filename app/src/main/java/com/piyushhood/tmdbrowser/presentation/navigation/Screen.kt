package com.piyushhood.tmdbrowser.presentation.navigation

sealed class Screen(val route : String){
    data object Home : Screen("home")
    data object MovieDetails : Screen("movie_details")
    data object Search : Screen("search")
    data object Settings : Screen("settings")
}