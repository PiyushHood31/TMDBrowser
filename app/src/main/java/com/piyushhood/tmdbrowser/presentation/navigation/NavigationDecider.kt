package com.piyushhood.tmdbrowser.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

fun decideNavigationType(
    widthSizeClass: WindowWidthSizeClass
) : NavigationType{
    return when(widthSizeClass){
        WindowWidthSizeClass.Compact -> NavigationType.BOTTOM_BAR
        WindowWidthSizeClass.Medium -> NavigationType.NAVIGATION_RAIL
        WindowWidthSizeClass.Expanded -> NavigationType.NAVIGATION_DRAWER
        else -> NavigationType.BOTTOM_BAR
    }
}