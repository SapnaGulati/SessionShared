package com.example.sharedpreferencesession.ui.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sharedpreferencesession.R

class MoviesAdapter(private val movies: List<Result>) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}

class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.movie_photo)
    private val title: TextView = itemView.findViewById(R.id.movie_title)
    private val overview: TextView = itemView.findViewById(R.id.movie_overview)
    private val rating: TextView = itemView.findViewById(R.id.movie_rating)
    fun bind(movie: Result) {
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .into(photo)
        title.text = "Title: " + movie.title
        overview.text = movie.overview
        rating.text = "Rating : " + movie.vote_average.toString()
    }
}