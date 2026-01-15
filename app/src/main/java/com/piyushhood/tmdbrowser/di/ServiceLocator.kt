package com.piyushhood.tmdbrowser.di

import com.piyushhood.tmdbrowser.data.remote.api.TmdbService
import com.piyushhood.tmdbrowser.data.remote.provideRetrofit
import okhttp3.OkHttpClient

object ServiceLocator {
    private val okHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit by lazy {
        provideRetrofit(okHttpClient = okHttpClient)
    }

    val tmdbService: TmdbService by lazy {
        retrofit.create(TmdbService::class.java)
    }
}