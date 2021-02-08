package com.xathordroid.popcornroom.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xathordroid.popcornroom.data.local.entity.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun loadMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<Movie>)
}