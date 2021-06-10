package com.lukman.jetpackfinal.data.source.remote.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lukman.jetpackfinal.vo.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>() {


    private val result : Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading)
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)){
            emit(Resource.Loading)
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Failure -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.message ?: ""))
                }
            }
        }else{
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    private fun onFetchFailed() {}

    protected abstract suspend fun loadFromDB(): Flow<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType?)

    fun asLiveData() : LiveData<Resource<ResultType>> = result.asLiveData()


}