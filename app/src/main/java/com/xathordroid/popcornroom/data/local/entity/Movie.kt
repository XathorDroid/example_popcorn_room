package com.xathordroid.popcornroom.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)