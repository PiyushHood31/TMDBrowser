package com.piyushhood.tmdbrowser.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.piyushhood.tmdbrowser.R

data class TopLevelDestination(
    val route: String,
    val icon: ImageVector,
    @StringRes val labelRes: Int
)

val topLevelDestinations = listOf(
    TopLevelDestination(
        route = Screen.Home.route,
        icon = Icons.Filled.Home,
        labelRes = R.string.nav_home
    ),
    TopLevelDestination(
        route = Screen.Search.route,
        icon = Icons.Filled.Search,
        labelRes = R.string.nav_search
    ),
    TopLevelDestination(
        route = Screen.Settings.route,
        icon = Icons.Filled.Settings,
        labelRes = R.string.nav_settings
    )
)

