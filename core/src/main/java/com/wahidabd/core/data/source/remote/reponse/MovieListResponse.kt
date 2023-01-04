package com.wahidabd.core.data.source.remote.reponse

data class MovieListResponse(
    val pages: Int,
    val total_pages: Int,
    val total_results: Int,
    val results: List<MovieResponse>
)