package com.piyushhood.tmdbrowser.data.mapper

import com.piyushhood.tmdbrowser.data.remote.dto.MovieDto
import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.model.MovieId

fun MovieDto.toDomain() : Movie {
    return Movie (
        id = MovieId(id),
        title = title.ifBlank { originalTitle },
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        rating = voteAverage
    )
}

fun List<MovieDto>.toDomain() : List<Movie> =
    map{it.toDomain()}