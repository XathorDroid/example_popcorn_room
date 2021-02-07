package com.xathordroid.popcornroom.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xathordroid.popcornroom.R
import com.xathordroid.popcornroom.data.remote.ApiConstants
import com.xathordroid.popcornroom.data.local.entity.Movie

class MyMovieRecyclerViewAdapter(private val context: Context, private var values: List<Movie>) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        Glide.with(context)
            .load(ApiConstants.IMAGE_BASE_URL + item.poster_path)
            .into(holder.ivCover)
    }

    override fun getItemCount(): Int {
        if (values != null) {
            return values.size
        } else {
            return 0
        }
    }

    fun setData(movies: List<Movie>) {
        values = movies
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.ivCover)
    }
}