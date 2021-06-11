package com.lukman.jetpackfinal.data.source.remote.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import com.lukman.jetpackfinal.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.Loading()
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()
        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch(it)) {
                CoroutineScope(Dispatchers.Main).launch {
                    fetchFromNetwork(dbSource)
                }
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.Success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    private suspend fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) {
            result.value = Resource.Loading()
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success ->
                    CoroutineScope(Dispatchers.IO).launch {
                        response.data?.let { saveCallResult(it) }
                        CoroutineScope(Dispatchers.Main).launch {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.Success(newData)
                            }
                        }
                    }

                is ApiResponse.Failure -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.Error(response.message ?: "")
                    }
                }
            }
        }
    }

    fun toLiveData(): LiveData<Resource<ResultType>> = result
}