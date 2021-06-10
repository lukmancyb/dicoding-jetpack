package com.lukman.jetpackfinal.di

import com.lukman.jetpackfinal.Constants.API_KEY
import com.lukman.jetpackfinal.Constants.BASE_URL
import com.lukman.jetpackfinal.data.source.remote.RemoteDataSource
import com.lukman.jetpackfinal.data.source.remote.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingIntercaptorOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @LoggingIntercaptorOkHttpClient
    @Singleton
    @Provides
    fun provideLoggingInterceptor() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
//                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
//                else
//                    HttpLoggingInterceptor.Level.NONE
            )
        )
        .addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
                .build()
            chain.proceed(requestBuilder)
        })
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(
        @LoggingIntercaptorOkHttpClient okHttpClient: OkHttpClient
    ): ApiServices = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServices::class.java)


}