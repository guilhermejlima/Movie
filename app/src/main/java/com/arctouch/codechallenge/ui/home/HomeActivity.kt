package com.arctouch.codechallenge.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer


class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()


    private val listMovieObserver = Observer<List<Movie>> { movie ->
        if (movie.isEmpty()) showDefaultMessage() else onUpdateMovieByApi(movie)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        initObservables()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.updateGenres()
        homeViewModel.updateList()
    }

    private fun onUpdateMovieByApi(listMovie: List<Movie>) {
        recyclerView.adapter = HomeAdapter(listMovie)
        progressBar.visibility = View.GONE
    }

    private fun initObservables() {
        homeViewModel.getUpcomingMovies().observe(this, listMovieObserver)
    }

    private fun showDefaultMessage() {}


}
