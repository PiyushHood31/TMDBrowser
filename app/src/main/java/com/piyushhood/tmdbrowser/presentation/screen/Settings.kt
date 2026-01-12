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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.piyushhood.tmdbrowser.R
import com.piyushhood.tmdbrowser.domain.model.AppLanguage
import com.piyushhood.tmdbrowser.domain.model.ThemeMode
import com.piyushhood.tmdbrowser.presentation.component.LanguageOption
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

    val languageState by preferencesViewModel
        .appLanguageState
        .collectAsState()
    val selectedLanguage = when(languageState){
        is UiState.Success ->
            (languageState as UiState.Success<AppLanguage>).data
        else -> AppLanguage.SYSTEM
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = stringResource(id = R.string.theme_title),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        ThemeOption(
            label = stringResource(id = R.string.theme_light),
            selected = selectedTheme == ThemeMode.LIGHT,
            onClick = {preferencesViewModel.updateThemeMode(ThemeMode.LIGHT)}
        )
        ThemeOption(
            label = stringResource(id = R.string.theme_dark),
            selected = selectedTheme == ThemeMode.DARK,
            onClick = {preferencesViewModel.updateThemeMode(ThemeMode.DARK)}
        )
        ThemeOption(
            label = stringResource(id = R.string.theme_system),
            selected = selectedTheme == ThemeMode.SYSTEM,
            onClick = {preferencesViewModel.updateThemeMode(ThemeMode.SYSTEM)}
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.language_title),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        LanguageOption(
            label = stringResource(id = R.string.language_english),
            selected = selectedLanguage == AppLanguage.ENGLISH,
            onClick = {
                preferencesViewModel.updateAppLanguage(AppLanguage.ENGLISH)
            }
        )

        LanguageOption(
            label = stringResource(id = R.string.language_hindi),
            selected = selectedLanguage == AppLanguage.HINDI,
            onClick = {
                preferencesViewModel.updateAppLanguage(AppLanguage.HINDI)
            }
        )

        LanguageOption(
            label = stringResource(R.string.language_system),
            selected = selectedLanguage == AppLanguage.SYSTEM,
            onClick = {
                preferencesViewModel.updateAppLanguage(AppLanguage.SYSTEM)
            }
        )



    }


}