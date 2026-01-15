package com.piyushhood.tmdbrowser.data.remote.api

import com.piyushhood.tmdbrowser.data.remote.dto.MovieDto
import com.piyushhood.tmdbrowser.data.remote.dto.MovieListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language : String,
        @Query("page") page : Int = 1
    ) : MovieListResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId : Int,
        @Query("language") language: String
    ) : MovieDto
}