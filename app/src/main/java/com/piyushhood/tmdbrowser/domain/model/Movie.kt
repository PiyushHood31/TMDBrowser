package com.piyushhood.tmdbrowser.domain.model

data class Movie(
    val id: MovieId,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val rating: Double
)
