package com.wahidabd.core.domain.usecase

import com.wahidabd.core.common.Resource
import com.wahidabd.core.domain.model.Movie
import com.wahidabd.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val repository: IMovieRepository) : MovieUseCase {

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        repository.getMovies()

    override fun getDetail(id: Int): Flow<Resource<Movie>> =
        repository.getDetail(id)

    override fun getFavorites(): Flow<List<Movie>> =
        repository.getFavorites()

    override fun checkFavorite(id: Int): Flow<Boolean> =
        repository.checkFavorite(id)

    override fun setFavorite(movie: Movie, newState: Boolean) {
        repository.setFavorite(movie, newState)
    }

    override fun searchMovie(query: String): Flow<List<Movie>> =
        repository.searchMovie(query)
}