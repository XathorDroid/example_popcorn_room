package com.xathordroid.popcornroom.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.xathordroid.popcornroom.R
import com.xathordroid.popcornroom.common.MyApp
import com.xathordroid.popcornroom.data.local.entity.Movie
import com.xathordroid.popcornroom.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private lateinit var moviesAdapter: MyMovieRecyclerViewAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var popularMovies: List<Movie> = ArrayList()

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        moviesAdapter = MyMovieRecyclerViewAdapter(MyApp.getContext(), popularMovies)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = moviesAdapter
            }
        }

        loadMovies()

        return view
    }

    private fun loadMovies() {
        movieViewModel.popularMovies().observe(viewLifecycleOwner, {
            it.data?.let { movies ->
                popularMovies = movies
                moviesAdapter.setData(popularMovies)
            }
        })
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}