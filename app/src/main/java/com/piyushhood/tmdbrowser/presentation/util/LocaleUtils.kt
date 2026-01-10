package com.piyushhood.tmdbrowser.presentation.util

import android.content.Context
import android.content.res.Configuration
import com.piyushhood.tmdbrowser.domain.model.AppLanguage
import java.util.Locale

fun Context.updateLocale(language: AppLanguage) : Context {
    if(language == AppLanguage.SYSTEM) return this

    val locale = Locale(language.languageCode)
    Locale.setDefault(locale)

    val config = Configuration(resources.configuration)
    config.setLocale(locale)

    return createConfigurationContext(config)
}