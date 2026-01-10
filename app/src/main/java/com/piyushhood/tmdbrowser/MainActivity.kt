package com.piyushhood.tmdbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.piyushhood.tmdbrowser.data.repository.PreferencesRepositoryImpl
import com.piyushhood.tmdbrowser.domain.model.AppLanguage
import com.piyushhood.tmdbrowser.domain.model.ThemeMode
import com.piyushhood.tmdbrowser.presentation.navigation.AppNavGraph
import com.piyushhood.tmdbrowser.presentation.scaffold.AdaptiveScaffold
import com.piyushhood.tmdbrowser.presentation.state.UiState
import com.piyushhood.tmdbrowser.presentation.theme.TMDBrowserTheme
import com.piyushhood.tmdbrowser.presentation.util.updateLocale
import com.piyushhood.tmdbrowser.presentation.viewmodel.PreferencesViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val preferencesRepository = remember {
                PreferencesRepositoryImpl(context)
            }
            val preferencesViewModel = remember {
                PreferencesViewModel(preferencesRepository)
            }

            val languageState by preferencesViewModel
                .appLanguageState
                .collectAsState()

            val appLanguage = when(languageState){
                is UiState.Success ->
                    (languageState as UiState.Success<AppLanguage>).data
                else -> AppLanguage.SYSTEM
            }
            val themeState by preferencesViewModel
                .themeModeState
                .collectAsState()
            val themeMode = when(themeState){
                is UiState.Success ->(themeState as UiState.Success<ThemeMode>).data
                else -> ThemeMode.SYSTEM
            }
            val localizeContext = remember (appLanguage){
                context.updateLocale(appLanguage)
            }

            val windowSizeClass = calculateWindowSizeClass(this)
            CompositionLocalProvider(
                LocalContext provides localizeContext
            ) {
                TMDBrowserTheme(themeMode = themeMode) {
                    AdaptiveScaffold(
                        windowSizeClass = windowSizeClass
                    ) { modifier ->

                        AppNavGraph(
                            navController = rememberNavController(),
                            preferencesViewModel = preferencesViewModel,
                            modifier = modifier
                        )
                    }

                }
            }
        }
    }
}
