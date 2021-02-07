package com.xathordroid.popcornroom.data.remote

import com.xathordroid.popcornroom.data.remote.model.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>
}