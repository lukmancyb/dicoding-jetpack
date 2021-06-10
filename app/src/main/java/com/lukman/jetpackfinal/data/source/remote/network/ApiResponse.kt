package com.lukman.jetpackfinal.data.source.remote.network

import okhttp3.ResponseBody

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T?) : ApiResponse<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val responseBody: ResponseBody?,
        val message : String? =""

    ) : ApiResponse<Nothing>()
}