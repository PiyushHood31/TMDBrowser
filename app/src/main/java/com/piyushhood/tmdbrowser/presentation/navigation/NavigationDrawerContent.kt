package com.piyushhood.tmdbrowser.presentation.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationDrawerContent(
    navController: NavController,
    onDestinationClick : () -> Unit
){
    val backStackEntry  = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(12.dp))

        topLevelDestinations.forEach{destination ->
            val selected = currentRoute == destination.route

            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(destination.labelRes))
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.labelRes)
                    )
                },
                selected = selected,
                onClick = {
                    if(!selected){
                        navController.navigate(destination.route){
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    onDestinationClick()
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )

            )
        }
    }
}