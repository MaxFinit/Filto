package com.maxfin.filto.network.base


import retrofit2.Response

abstract class BaseNetwork {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Success(body)
                } else {
                    error(Exception("Data error.\nCode: ${response.code()}.\nMessage: ${response.message()}"))
                }
            } else {
                error(Exception("Unknown error.\nCode: ${response.code()}\nMessage: ${response.message()}"))
            }
        } catch (ex: Exception) {
            error(ex)
        }
    }

    private fun <T> error(message: Exception): NetworkResult<T> {
        return NetworkResult.Error(message)
    }

}