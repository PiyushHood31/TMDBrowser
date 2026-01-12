package com.piyushhood.tmdbrowser.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

fun decideNavigationType(
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass
): NavigationType {
    return when {
        widthSizeClass == WindowWidthSizeClass.Compact ->
            NavigationType.BOTTOM_BAR

        widthSizeClass == WindowWidthSizeClass.Medium ->
            NavigationType.NAVIGATION_RAIL

        widthSizeClass == WindowWidthSizeClass.Expanded &&
                heightSizeClass == WindowHeightSizeClass.Compact ->
            NavigationType.NAVIGATION_RAIL // landscape phone

        widthSizeClass == WindowWidthSizeClass.Expanded ->
            NavigationType.NAVIGATION_DRAWER // tablet

        else -> NavigationType.BOTTOM_BAR
    }
}
