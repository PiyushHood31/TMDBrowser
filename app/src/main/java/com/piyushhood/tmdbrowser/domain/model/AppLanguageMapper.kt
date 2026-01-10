package com.piyushhood.tmdbrowser.domain.model

fun AppLanguage.toStorageValue() : String = name
fun String.toAppLanguage() : AppLanguage =
    runCatching { AppLanguage.valueOf(this) }
        .getOrDefault(AppLanguage.SYSTEM)