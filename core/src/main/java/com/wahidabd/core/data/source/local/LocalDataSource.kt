package com.wahidabd.core.data.source.local

import com.wahidabd.core.data.source.local.entity.MovieEntity
import com.wahidabd.core.data.source.local.room.MovieDao
import com.wahidabd.core.data.source.local.room.MovieDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val database: MovieDatabase) {

    fun getMovies(): Flow<List<MovieEntity>> = database.movieDao().getMovies()
    fun getFavorites(): Flow<List<MovieEntity>> = database.movieDao().getFavorites()
    fun getMovie(id: String): Flow<MovieEntity> = database.movieDao().getMovie(id)
    suspend fun insertMovie(movie: List<MovieEntity>) = database.movieDao().insertMovie(movie)
    fun setFavorite(movie: MovieEntity, newState: Boolean){
        movie.is_favorite = newState
        database.movieDao().updateMovie(movie)
    }
    fun searchMovie(query: String): Flow<List<MovieEntity>> = database.movieDao().search(query)

}