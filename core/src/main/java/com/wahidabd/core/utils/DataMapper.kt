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

    fun mapEntitiesToDomain(data: List<MovieEntity>): List<MovieModel> {
        return data.map { entity ->
            MovieModel(
                id = entity.id,
                title = entity.title.toString(),
                overview = entity.overview.toString(),
                poster_path = entity.poster_path.toString(),
                backdrop_path = entity.backdrop_path.toString(),
                release_date = entity.release_date.toString(),
                vote_average = entity.vote_average,
                vote_count = entity.vote_count,
                original_language = entity.original_language.toString(),
                is_favorite = entity.is_favorite
            )
        }
    }

    fun mapDomainToEntity(data: MovieModel) =
        MovieEntity(
            id = data.id,
            title = data.title,
            overview = data.overview,
            poster_path = data.poster_path,
            backdrop_path = data.backdrop_path,
            release_date = data.release_date,
            vote_average = data.vote_average,
            vote_count = data.vote_count,
            original_language = data.original_language,
            is_favorite = data.is_favorite
        )

}