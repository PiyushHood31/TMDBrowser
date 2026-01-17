package com.piyushhood.tmdbrowser.data.local.entity

import com.piyushhood.tmdbrowser.domain.model.Movie
import com.piyushhood.tmdbrowser.domain.model.MovieId

fun MovieEntity.toDomain() : Movie {
    return Movie(
        id = MovieId(id),
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        backdropPath = backdropPath,
        rating = voteAverage,
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id.value,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = rating,
        popularity = 0.0,          // safe default
        originalLanguage = "en"     // or persisted separately
    )
}