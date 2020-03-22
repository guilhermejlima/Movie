package com.arctouch.codechallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.util.Constants.ID_MOVIE
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel : DetailViewModel by viewModel()

    private val movieImageUrlBuilder = MovieImageUrlBuilder()

    private var idMovie: Int? = 0

    private val movieObserve = Observer<Movie>{
        createDetailScreen(it)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            idMovie = it?.getInt(ID_MOVIE)
        }
        initObservables()
    }


    private fun initObservables() {
        idMovie?.toLong()?.let { detailViewModel.getMovie(it).observe(this,movieObserve ) }
    }

    private fun createDetailScreen(movie: Movie?) {
        image_view_poster_detail_fragment.visibility = VISIBLE

        movie?.backdropPath?.let { createImageBackdrop(it) }
        movie?.posterPath?.let { createImagePoster(it) }
        text_view_title_detail_fragment.text = movie?.title
        text_view_genre_detail_fragment.text = movie?.genres?.joinToString(separator = ", ") {it.name}
        text_view_release_detail_fragment.text = movie?.releaseDate
        progress_bar_detail_fragment.visibility = GONE
    }

    private fun createImageBackdrop(backdroPpath: String){
        val image = movieImageUrlBuilder.buildBackdropUrl(backdroPpath)
        Glide
                .with(this)
                .load(image)
                .into(image_view_poster_detail_fragment)


    }

    private fun createImagePoster(posterPath: String){
        val posterImage = movieImageUrlBuilder.buildPosterUrl(posterPath)
        Glide
                .with(this)
                .load(posterImage)
                .into(image_view_cover_fragment)

    }

    companion object {
        fun newInstance(idMovie: Int) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ID_MOVIE, idMovie)
                    }
                }
    }

}
