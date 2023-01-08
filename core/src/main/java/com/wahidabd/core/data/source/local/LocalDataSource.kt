package com.wahidabd.core.data.source.local

import com.wahidabd.core.data.source.local.entity.MovieEntity
import com.wahidabd.core.data.source.local.room.MovieDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val database: MovieDatabase) {

    fun getMovies(): Flow<List<MovieEntity>> = database.movieDao().getMovies()
    fun getFavorites(): Flow<List<MovieEntity>> = database.movieDao().getFavorites()
    fun getMovie(id: Int): Flow<MovieEntity> = database.movieDao().getMovie(id)
    fun checkFavorite(id: Int): Flow<Boolean> = database.movieDao().checkFavorite(id)
    suspend fun insertMovie(movie: List<MovieEntity>) = database.movieDao().insertMovie(movie)
    suspend fun insertDetail(movie: MovieEntity) = database.movieDao().insertDetail(movie)
    fun setFavorite(movie: MovieEntity, newState: Boolean){
        movie.is_favorite = newState
        database.movieDao().updateMovie(movie)
    }
    fun searchMovie(query: String): Flow<List<MovieEntity>> = database.movieDao().search(query)

}