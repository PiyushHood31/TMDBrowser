package com.piyushhood.tmdbrowser.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.piyushhood.tmdbrowser.domain.model.ThemeMode
import com.piyushhood.tmdbrowser.presentation.component.ThemeOption
import com.piyushhood.tmdbrowser.presentation.state.UiState
import com.piyushhood.tmdbrowser.presentation.viewmodel.PreferencesViewModel

@Composable
fun SettingsScreen(
    preferencesViewModel: PreferencesViewModel
){
    val themeState by preferencesViewModel
        .themeModeState
        .collectAsState()

    val selectedTheme = when(themeState){
        is UiState.Success ->
            (themeState as UiState.Success<ThemeMode>).data
        else -> ThemeMode.SYSTEM
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = "Theme",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        ThemeOption(
            label = "Light",
            selected = selectedTheme == ThemeMode.LIGHT,
            onClick = {preferencesViewModel.updateThemeMode(ThemeMode.LIGHT)}
        )
        ThemeOption(
            label = "Dark",
            selected = selectedTheme == ThemeMode.DARK,
            onClick = {preferencesViewModel.updateThemeMode(ThemeMode.DARK)}
        )
        ThemeOption(
            label = "System",
            selected = selectedTheme == ThemeMode.SYSTEM,
            onClick = {preferencesViewModel.updateThemeMode(ThemeMode.SYSTEM)}
        )
    }


}