package com.piyushhood.tmdbrowser.data.remote.api

import com.piyushhood.tmdbrowser.data.remote.dto.MovieListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey : String,
        @Query("language") language : String,
        @Query("page") page : Int = 1
    ) : MovieListResponseDto
}