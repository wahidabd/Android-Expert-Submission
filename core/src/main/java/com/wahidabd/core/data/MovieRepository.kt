package com.wahidabd.core.data

import com.wahidabd.core.common.Resource
import com.wahidabd.core.data.NetworkBoundResource
import com.wahidabd.core.data.source.local.LocalDataSource
import com.wahidabd.core.data.source.remote.RemoteDataSource
import com.wahidabd.core.data.source.remote.reponse.MovieListResponse
import com.wahidabd.core.data.source.remote.reponse.MovieResponse
import com.wahidabd.core.domain.model.MovieModel
import com.wahidabd.core.domain.repository.IMovieRepository
import com.wahidabd.core.utils.AppExecutors
import com.wahidabd.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getMovies(): Flow<Resource<List<MovieModel>>> =
        object : NetworkBoundResource<List<MovieModel>, MovieListResponse>() {
            override fun loadFromDB(): Flow<List<MovieModel>> =
                local.getMovies().map { data ->
                    DataMapper.mapEntitiesToDomain(data)
                }

            override fun shouldFetch(data: List<MovieModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<Resource<MovieListResponse>> =
                remote.getMovies()

            override suspend fun saveCallResult(data: MovieListResponse) {
                val movies = DataMapper.mapResponseToEntities(data.results)
                local.insertMovie(movies)
            }

        }.asFlow()

    override fun getDetail(id: Int): Flow<Resource<MovieModel>> =
        object : NetworkBoundResource<MovieModel, MovieResponse>() {
            override fun loadFromDB(): Flow<MovieModel> =
                local.getMovie(id).map { data ->
                    DataMapper.mapEntityToDomain(data)
                }

            override fun shouldFetch(data: MovieModel?): Boolean =
                data == null

            override suspend fun createCall(): Flow<Resource<MovieResponse>> =
                remote.getDetail(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.mapResponseToEntity(data)
                local.insertDetail(movie)
            }

        }.asFlow()

    override fun getFavorites(): Flow<List<MovieModel>> =
        local.getFavorites().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun checkFavorite(id: Int): Flow<MovieModel> =
        local.checkFavorite(id)

    override fun setFavorite(movie: MovieModel, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            local.setFavorite(movieEntity, newState)
        }
    }

    override fun searchMovie(query: String): Flow<List<MovieModel>> =
        local.searchMovie(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
}