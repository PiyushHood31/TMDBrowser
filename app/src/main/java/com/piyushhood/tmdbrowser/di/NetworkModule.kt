package com.piyushhood.tmdbrowser.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.piyushhood.tmdbrowser.BuildConfig
import com.piyushhood.tmdbrowser.data.remote.TmdbConstants
import com.piyushhood.tmdbrowser.data.remote.api.TmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApikeyInterceptor() : okhttp3.Interceptor =
        okhttp3.Interceptor{chain ->
            val original = chain.request()
            val newUrl = original.url.newBuilder()
                .addQueryParameter("api_key" , BuildConfig.TMDB_API_KEY)
                .build()

            val request = original.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        apiKeyInterceptor: okhttp3.Interceptor
    ) : OkHttpClient{
        val logging = HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor (logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson() : Json = Json{
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json : Json
    ) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(TmdbConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()

    }

    @Provides
    @Singleton
    fun provideTmdbService(
        retrofit: Retrofit
    ) : TmdbService{
        return retrofit.create(TmdbService::class.java)
    }
}