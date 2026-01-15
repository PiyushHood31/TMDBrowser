package com.piyushhood.tmdbrowser.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun provideOkHttpClient() : OkHttpClient {
    val logging  = HttpLoggingInterceptor().apply(){
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .addInterceptor (ApiKeyInterceptor())
        .addInterceptor(logging)
        .build()
}