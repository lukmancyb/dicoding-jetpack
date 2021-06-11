package com.lukman.jetpackfinal.vo

sealed class Resource<out T>(val data: T? = null, val message: String?= null) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error(message: String?) : Resource<Nothing>(null, message)
    class Loading : Resource<Nothing>()
}