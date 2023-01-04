package com.wahidabd.core.utils

import com.wahidabd.core.data.source.local.entity.MovieEntity
import com.wahidabd.core.data.source.remote.reponse.MovieResponse
import com.wahidabd.core.domain.model.MovieModel

object DataMapper {

    fun mapResponseToEntities(data: List<MovieResponse>): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        data.map { movieResponse ->
            val movie = MovieEntity(
                id = movieResponse.id,
                title = movieResponse.title,
                overview = movieResponse.overview,
                poster_path = movieResponse.poster_path,
                backdrop_path = movieResponse.backdrop_path,
                release_date = movieResponse.release_date,
                vote_average = movieResponse.vote_average,
                vote_count = movieResponse.vote_count,
                original_language = movieResponse.original_language,
                is_favorite = false
            )
            movies.add(movie)
        }
        return movies
    }

//    fun mapEntitiesToDomain(data: List<MovieEntity>): List<MovieModel> {}

}