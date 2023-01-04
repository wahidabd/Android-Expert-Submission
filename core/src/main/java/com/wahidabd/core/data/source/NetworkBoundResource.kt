package com.wahidabd.core.data.source

import com.wahidabd.core.common.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<Response, Request> {

    private var result: Flow<Resource<Response>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val response = createCall().first()){
                is Resource.Loading -> {}
                is Resource.Error -> {
                    onFetchFailed()
                    emit(
                        Resource.Error(
                            response.error
                        )
                    )
                }
                is Resource.Success -> {
                    saveCallResult(response.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<Response>

    protected abstract fun shouldFetch(data: Response?): Boolean

    protected abstract suspend fun createCall(): Flow<Resource<Request>>

    protected abstract suspend fun saveCallResult(data: Request)

    fun asFlow(): Flow<Resource<Response>> = result

}