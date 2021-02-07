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
import com.xathordroid.popcornroom.data.local.Movie

class MyMovieRecyclerViewAdapter(private val context: Context, private val values: List<Movie>) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

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

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.ivCover)
    }
}