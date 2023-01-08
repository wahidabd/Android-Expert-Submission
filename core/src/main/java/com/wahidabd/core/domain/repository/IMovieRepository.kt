package com.wahidabd.core.domain.repository

import com.wahidabd.core.common.Resource
import com.wahidabd.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getDetail(id: Int): Flow<Resource<Movie>>
    fun getFavorites(): Flow<List<Movie>>
    fun checkFavorite(id: Int): Flow<Boolean>
    fun setFavorite(movie: Movie, newState: Boolean)
    fun searchMovie(query: String): Flow<List<Movie>>

}