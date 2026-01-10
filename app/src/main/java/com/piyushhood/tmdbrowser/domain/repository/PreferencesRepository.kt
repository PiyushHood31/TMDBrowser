package com.piyushhood.tmdbrowser.domain.repository

import com.piyushhood.tmdbrowser.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val themeMode : Flow<ThemeMode>
    suspend fun setThemeMode(themeMode: ThemeMode)
}