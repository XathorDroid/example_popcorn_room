package com.xathordroid.popcornroom.data

import com.xathordroid.popcornroom.data.remote.ApiConstants
import com.xathordroid.popcornroom.data.remote.MovieApiService
import com.xathordroid.popcornroom.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    var movieApiService: MovieApiService? = null

    init {
        // RequestInterceptor
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(RequestInterceptor())
        val client = okHttpClientBuilder.build()

        // Remote > Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.TMDBAPI_BAASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieApiService = retrofit.create(MovieApiService::class.java)
    }
}