package com.lukman.jetpackfinal.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false

)
abstract class MyDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}