package com.xathordroid.popcornroom.data.remote.model

import com.xathordroid.popcornroom.data.local.entity.Movie

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)