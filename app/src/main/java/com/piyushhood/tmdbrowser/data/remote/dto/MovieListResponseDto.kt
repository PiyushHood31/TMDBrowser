package com.piyushhood.tmdbrowser.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponseDto(
    val page : Int = 1,
    val results : List<MovieDto> = emptyList(),
    @SerialName("total_page")
    val totalPages : Int = 1,
    @SerialName("total_results")
    val totalResults : Int = 0
)
