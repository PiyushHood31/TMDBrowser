package com.piyushhood.tmdbrowser.presentation.scaffold

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.piyushhood.tmdbrowser.presentation.navigation.BottomNavBar
import com.piyushhood.tmdbrowser.presentation.navigation.NavigationDrawerContent
import com.piyushhood.tmdbrowser.presentation.navigation.NavigationRailBar
import com.piyushhood.tmdbrowser.presentation.navigation.NavigationType
import com.piyushhood.tmdbrowser.presentation.navigation.decideNavigationType
import kotlinx.coroutines.launch

@Composable
fun AdaptiveScaffold(
    windowSizeClass: WindowSizeClass,
    navController: NavController,
    content: @Composable (Modifier) -> Unit
) {
    val navigationType = decideNavigationType(windowSizeClass.widthSizeClass)

    when (navigationType) {
        NavigationType.BOTTOM_BAR -> {
            Scaffold(
                bottomBar = {
                    BottomNavBar (navController = navController)

                }
            ) { paddingValues ->
                content(Modifier.padding(paddingValues))
            }
        }

        NavigationType.NAVIGATION_RAIL -> {
            Row {
                NavigationRailBar(navController)
                content(Modifier)
            }
        }

        NavigationType.NAVIGATION_DRAWER -> {
            val drawerState = rememberDrawerState(
                initialValue = DrawerValue.Closed
            )
            val scope = rememberCoroutineScope()

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    NavigationDrawerContent(
                        navController = navController,
                        onDestinationClick = {
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            ) {
                content(Modifier)
            }
        }
    }
}