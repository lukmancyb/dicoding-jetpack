package com.lukman.jetpackfinal.di

import android.content.Context
import androidx.room.Room
import com.lukman.jetpackfinal.data.source.local.LocalDataSource
import com.lukman.jetpackfinal.data.source.local.database.MovieDao
import com.lukman.jetpackfinal.data.source.local.database.MyDatabase
import com.lukman.jetpackfinal.data.source.remote.RemoteDataSource
import com.lukman.jetpackfinal.data.source.remote.network.ApiHelper
import com.lukman.jetpackfinal.data.source.remote.network.ApiHelperImpl
import com.lukman.jetpackfinal.data.source.remote.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideApiHelper(apiHelper : ApiHelperImpl) : ApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiHelper: ApiHelper) = RemoteDataSource(apiHelper)


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "moviedb.db"
        ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db : MyDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(dao : MovieDao) = LocalDataSource(dao)







}