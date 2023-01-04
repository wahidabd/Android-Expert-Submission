package com.wahidabd.core.common

import okhttp3.ResponseBody
import retrofit2.Response
import java.net.SocketTimeoutException

class SafeCall {

    suspend fun <T> enqueue(
        converter: (ResponseBody) -> GenericResponse?,
        call: suspend () -> Response<T>
    ): Resource<T> = try {
        val res = call()
        val body = res.body()
        val errBody = res.errorBody()

        if (res.isSuccessful && body != null){
            Resource.Success(body)
        }else if (errBody != null){
            val parsedError = converter(errBody)
            if (parsedError != null){
                Resource.Error(parsedError.message)
            }else{
                Resource.Error(ErrorMessage.UNKNOWN_ERROR)
            }
        }else{
            Resource.Error(ErrorMessage.UNKNOWN_ERROR)
        }

    }catch (e: Exception){
        when(e){
            is SocketTimeoutException -> Resource.Error(ErrorMessage.TIMEOUT_ERROR)
            else -> Resource.Error(ErrorMessage.UNKNOWN_ERROR)
        }
    }

    suspend fun <U, T> enqueue(
        req: U,
        converter: (ResponseBody) -> GenericResponse?,
        call: suspend (U) -> Response<T>
    ): Resource<T> = try {
        val res = call(req)
        val body = res.body()
        val errBody = res.errorBody()

        if (res.isSuccessful && body != null){
            Resource.Success(body)
        }else if (errBody != null){
            val parsedError = converter(errBody)
            Resource.Error(parsedError?.message ?: ErrorMessage.UNKNOWN_ERROR)
        }else{
            Resource.Error(ErrorMessage.UNKNOWN_ERROR)
        }

    }catch (e: Exception){
        when(e){
            is SocketTimeoutException -> Resource.Error(ErrorMessage.TIMEOUT_ERROR)
            else -> Resource.Error(ErrorMessage.UNKNOWN_ERROR)
        }
    }
}