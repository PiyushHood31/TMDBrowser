package com.piyushhood.tmdbrowser.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.piyushhood.tmdbrowser.data.remote.api.TmdbService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
    val json = Json{
        ignoreUnknownKeys = true
        isLenient = true
    }

    return Retrofit.Builder()
        .baseUrl(TmdbConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
}