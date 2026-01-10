package com.piyushhood.tmdbrowser.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.piyushhood.tmdbrowser.domain.model.ThemeMode

private val DarkColorScheme = darkColorScheme(
    primary = ImdbYellow,
    onPrimary = ImdbBlack,

    secondary = ImdbYellow,
    onSecondary = ImdbBlack,

    background = ImdbDarkBackground,
    onBackground = ImdbDarkTextPrimary,

    surface = ImdbDarkSurface,
    onSurface = ImdbDarkTextPrimary,

    tertiary = ImdbYellow,
    onTertiary = ImdbBlack
)

private val LightColorScheme = lightColorScheme(
    primary = ImdbYellow,
    onPrimary = ImdbBlack,

    secondary = ImdbYellow,
    onSecondary = ImdbBlack,

    background = ImdbLightBackground,
    onBackground = ImdbLightTextPrimary,

    surface = ImdbLightSurface,
    onSurface = ImdbLightTextPrimary,

    tertiary = ImdbYellow,
    onTertiary = ImdbBlack
)

@Composable
fun TMDBrowserTheme(
    themeMode: ThemeMode,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode){
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM ->isSystemInDarkTheme()
    }

    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}