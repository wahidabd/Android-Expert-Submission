package com.wahidabd.core.domain.repository

import com.wahidabd.core.common.Resource
import com.wahidabd.core.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getMovies(): Flow<Resource<List<MovieModel>>>
    fun getDetail(id: Int): Flow<Resource<MovieModel>>
    fun getFavorites(): Flow<List<MovieModel>>
    fun checkFavorite(id: Int): Flow<MovieModel>
    fun setFavorite(movie: MovieModel, newState: Boolean)
    fun searchMovie(query: String): Flow<List<MovieModel>>

}