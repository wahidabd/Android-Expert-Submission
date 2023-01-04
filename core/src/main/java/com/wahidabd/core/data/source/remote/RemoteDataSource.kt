package com.wahidabd.core.data.source.remote

import com.wahidabd.core.common.ErrorParser
import com.wahidabd.core.common.MyDispatchers
import com.wahidabd.core.common.Resource
import com.wahidabd.core.common.SafeCall
import com.wahidabd.core.data.source.remote.network.MovieService
import com.wahidabd.core.data.source.remote.reponse.MovieListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val safeCall: SafeCall,
    private val converter: ErrorParser,
    private val dispatchers: MyDispatchers,
    private val service: MovieService
) {

    suspend fun getMovies(): Flow<Resource<MovieListResponse>> = flow {

        val res = safeCall.enqueue(converter::converterGenericError, service::getMovies)
        emit(res)
    }.flowOn(dispatchers.io)

}