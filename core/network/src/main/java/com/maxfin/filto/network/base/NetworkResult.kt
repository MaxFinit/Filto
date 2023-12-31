package com.maxfin.filto.network.base

sealed class NetworkResult<out R> {

    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()

}