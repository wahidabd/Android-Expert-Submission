package com.wahidabd.core.domain.model

data class MovieModel(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val is_favorite: Boolean
)
