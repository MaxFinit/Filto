package com.maxfin.filto.common

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class Loading(val isLoad : Boolean,val progress : Int = 0) : Result<Nothing>()

}