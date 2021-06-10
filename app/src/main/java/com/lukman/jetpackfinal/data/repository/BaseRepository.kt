package com.lukman.jetpackfinal.data.repository

import com.lukman.jetpackfinal.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Flow<ApiResponse<T>> = flow {
        try {
            emit(ApiResponse.Success(apiCall.invoke()))
        } catch (thorwable: Throwable) {
            when (thorwable) {
                is HttpException -> {
                    emit(
                        ApiResponse.Failure(
                            false,
                            thorwable.code(),
                            thorwable.response()?.errorBody()
                        )
                    )
                }
                else -> {
                    emit(ApiResponse.Failure(true, null, null))
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}