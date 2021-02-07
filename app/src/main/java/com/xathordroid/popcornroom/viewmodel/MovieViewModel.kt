package com.xathordroid.popcornroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xathordroid.popcornroom.data.MovieRepository
import com.xathordroid.popcornroom.data.local.entity.Movie
import com.xathordroid.popcornroom.data.network.Resource

class MovieViewModel: ViewModel() {

    private val movieRepository: MovieRepository
    private val popularMovies: LiveData<Resource<List<Movie>>>

    init {
        movieRepository = MovieRepository()
        popularMovies = movieRepository.getPopularMovies()
    }

    fun popularMovies(): LiveData<Resource<List<Movie>>> {
        return popularMovies
    }
}