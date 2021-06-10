package com.lukman.jetpackfinal.vo

sealed class Resource<out T>{
    class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}