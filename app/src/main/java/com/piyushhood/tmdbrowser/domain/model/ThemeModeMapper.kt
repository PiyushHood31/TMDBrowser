package com.piyushhood.tmdbrowser.domain.model

fun ThemeMode.toStorageValue() : String = name

fun String.toThemeMode() : ThemeMode =
    runCatching { ThemeMode.valueOf(this) }
        .getOrDefault(ThemeMode.SYSTEM)