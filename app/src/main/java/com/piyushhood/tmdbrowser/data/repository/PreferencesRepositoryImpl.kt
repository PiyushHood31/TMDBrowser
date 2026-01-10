package com.piyushhood.tmdbrowser.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.piyushhood.tmdbrowser.data.preferences.PreferenceKeys
import com.piyushhood.tmdbrowser.data.preferences.dataStore
import com.piyushhood.tmdbrowser.domain.model.AppLanguage
import com.piyushhood.tmdbrowser.domain.model.ThemeMode
import com.piyushhood.tmdbrowser.domain.model.toAppLanguage
import com.piyushhood.tmdbrowser.domain.model.toStorageValue
import com.piyushhood.tmdbrowser.domain.model.toThemeMode
import com.piyushhood.tmdbrowser.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl(
    private val context : Context
) : PreferencesRepository {
    override val themeMode: Flow<ThemeMode> =
        context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.THEME_MODE]
                ?.toThemeMode()
                ?: ThemeMode.SYSTEM
        }
    override val appLanguage: Flow<AppLanguage> =
        context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.LANGUAGE]
                ?.toAppLanguage()
                ?: AppLanguage.SYSTEM
        }

    override suspend fun setThemeMode(themeMode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.THEME_MODE] =
                themeMode.toStorageValue()
        }
    }

    override suspend fun setAppLanguage(language: AppLanguage) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.LANGUAGE] =
                language.toStorageValue()
        }
    }
}