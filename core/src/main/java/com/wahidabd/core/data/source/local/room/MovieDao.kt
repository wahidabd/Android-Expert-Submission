package com.wahidabd.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wahidabd.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM movie_table")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE is_favorite = 1")
    fun getFavorites(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovie(id: String): Flow<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE title LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<MovieEntity>>

}