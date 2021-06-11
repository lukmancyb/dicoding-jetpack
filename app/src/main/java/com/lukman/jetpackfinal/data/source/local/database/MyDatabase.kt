package com.lukman.jetpackfinal.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lukman.jetpackfinal.data.source.local.entity.MovieEntity


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false

)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "movie.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
    }

    abstract fun movieDao() : MovieDao
}