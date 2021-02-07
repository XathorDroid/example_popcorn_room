package com.xathordroid.popcornroom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xathordroid.popcornroom.data.local.dao.MovieDao
import com.xathordroid.popcornroom.data.local.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}