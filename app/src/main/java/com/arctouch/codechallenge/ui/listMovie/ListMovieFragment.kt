package com.arctouch.codechallenge.ui.listMovie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.ui.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.list_movie_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListMovieFragment : Fragment(), ListMovieAdapter.OnItemClickListener {
    private val listMovieViewModel: ListMovieViewModel by viewModel()

    private var listener: OnFragmentInteractionListener? = null

    private val listMovieObserver = Observer<List<Movie>> { movie ->
        if (movie.isEmpty()) showDefaultMessage() else onUpdateMovieByApi(movie)
    }

    companion object {
        fun newInstance() = ListMovieFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservables()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onResume() {
        super.onResume()
        listMovieViewModel.updateList()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_movie_fragment, container, false)
    }


    private fun initObservables() {
        listMovieViewModel.getUpcomingMovies().observe(this, listMovieObserver)
    }

    private fun onUpdateMovieByApi(listMovie: List<Movie>) {
        recyclerView.adapter = ListMovieAdapter(listMovie, this)
        progressBar.visibility = View.GONE
    }

    override fun onItemClickListener(movie: Movie) {
        listener?.goToDetailsFragment(movie.id)
    }

    private fun showDefaultMessage() {}


}
