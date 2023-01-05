package com.wahidabd.core.domain.usecase

import com.wahidabd.core.common.Resource
import com.wahidabd.core.domain.model.MovieModel
import com.wahidabd.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val repository: IMovieRepository) : MovieUseCase {

    override fun getMovies(): Flow<Resource<List<MovieModel>>> =
        repository.getMovies()

    override fun getFavorites(): Flow<List<MovieModel>> =
        repository.getFavorites()

    override fun setFavorite(movie: MovieModel, newState: Boolean) {
        repository.setFavorite(movie, newState)
    }

    override fun searchMovie(query: String): Flow<List<MovieModel>> =
        repository.searchMovie(query)
}