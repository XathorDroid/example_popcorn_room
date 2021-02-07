package com.xathordroid.popcornroom.data

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.xathordroid.popcornroom.common.MyApp
import com.xathordroid.popcornroom.data.local.MovieRoomDatabase
import com.xathordroid.popcornroom.data.local.dao.MovieDao
import com.xathordroid.popcornroom.data.local.entity.Movie
import com.xathordroid.popcornroom.data.network.NetworkBoundResource
import com.xathordroid.popcornroom.data.network.Resource
import com.xathordroid.popcornroom.data.remote.ApiConstants
import com.xathordroid.popcornroom.data.remote.MovieApiService
import com.xathordroid.popcornroom.data.remote.RequestInterceptor
import com.xathordroid.popcornroom.data.remote.model.PopularMoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Call
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

    fun getPopularMovies(): LiveData<Resource<List<Movie>>> {
        // first param is the response from ROOM
        // second param is the response from API
        return object : NetworkBoundResource<List<Movie>, PopularMoviesResponse>() {
            override fun loadFromDb(): LiveData<List<Movie>> {
                // Return data from ROOM
                return movieDao!!.loadMovies()
            }

            override fun saveCallResult(item: PopularMoviesResponse) {
                movieDao!!.saveMovies(item.results)
            }

            override fun createCall(): Call<PopularMoviesResponse> {
                // Obtain data from remote API
                return movieApiService!!.getPopularMovies()
            }
        }.asLiveData
    }
}