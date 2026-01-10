package com.piyushhood.tmdbrowser.data.preferences

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val PREFERENCE_NAME = "tmdbrowser_preferences"

val Context.dataStore by preferencesDataStore(
    name = PREFERENCE_NAME
)