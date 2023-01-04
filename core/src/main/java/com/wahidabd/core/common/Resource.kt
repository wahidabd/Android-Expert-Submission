package com.wahidabd.core.common

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Error<out T>(val error: String) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
}