package com.arctouch.codechallenge.ui.listMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class ListMovieAdapter (private val movies: List<Movie>, private val listener : OnItemClickListener) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val movieImageUrlBuilder = MovieImageUrlBuilder()

            fun bind(movie: Movie, listener : OnItemClickListener) {
                itemView.titleTextView.text = movie.title
                itemView.genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
                itemView.releaseDateTextView.text = movie.releaseDate
                itemView.setOnClickListener {
                    listener.onItemClickListener(movie)
                }

                Glide.with(itemView)
                        .load(movie.posterPath?.let { movieImageUrlBuilder.buildPosterUrl(it) })
                        .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                        .into(itemView.posterImageView)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount() = movies.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position], listener)

        interface OnItemClickListener {
            fun onItemClickListener(movie: Movie)
        }
}

