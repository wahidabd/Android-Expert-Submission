package com.wahidabd.core.data.source.remote.network

import com.wahidabd.core.data.source.remote.reponse.MovieListResponse
import com.wahidabd.core.data.source.remote.reponse.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getMovies(
    ): Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movie_id: Int
    ): Response<MovieResponse>

}