package com.xathordroid.popcornroom.data

import androidx.room.Room
import com.xathordroid.popcornroom.common.MyApp
import com.xathordroid.popcornroom.data.local.MovieRoomDatabase
import com.xathordroid.popcornroom.data.local.dao.MovieDao
import com.xathordroid.popcornroom.data.remote.ApiConstants
import com.xathordroid.popcornroom.data.remote.MovieApiService
import com.xathordroid.popcornroom.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    var movieApiService: MovieApiService? = null
    var movieDao: MovieDao? = null

    init {
        // Local > Room
        val movieRoomDatabase = Room.databaseBuilder(
            MyApp.getContext(),
            MovieRoomDatabase::class.java,
            "db_movies"
        ).build()
        movieDao = movieRoomDatabase.getMovieDao()

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