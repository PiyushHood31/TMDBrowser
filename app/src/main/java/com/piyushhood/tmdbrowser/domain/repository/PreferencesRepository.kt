package com.piyushhood.tmdbrowser.domain.repository

import com.piyushhood.tmdbrowser.domain.model.AppLanguage
import com.piyushhood.tmdbrowser.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val themeMode : Flow<ThemeMode>
    val appLanguage: Flow<AppLanguage>
    suspend fun setThemeMode(themeMode: ThemeMode)
    suspend fun setAppLanguage(language : AppLanguage)
}