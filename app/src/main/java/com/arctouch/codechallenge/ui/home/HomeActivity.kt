package com.arctouch.codechallenge.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.data.model.Movie
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.arctouch.codechallenge.ui.OnFragmentInteractionListener
import com.arctouch.codechallenge.ui.detail.DetailFragment
import com.arctouch.codechallenge.ui.listMovie.ListMovieFragment
import com.arctouch.codechallenge.util.Constants.DETAIL_FRAGMENT_TAG
import com.arctouch.codechallenge.util.Constants.LIST_MOVIE_FRAGMENT_TAG


class HomeActivity : AppCompatActivity(), OnFragmentInteractionListener, LifecycleOwner {


    private val fragmentManager by lazy {
        supportFragmentManager
    }

    private lateinit var fragmentTransaction : FragmentTransaction
    private var idMovie: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        replaceFragment(LIST_MOVIE_FRAGMENT_TAG)

    }

    override fun goToDetailsFragment(id: Int){
        idMovie = id
        fragmentManager.popBackStack()
        replaceFragment(DETAIL_FRAGMENT_TAG)
    }


    private fun replaceFragment(fragmentCode : String){
        val fragment : Fragment? = when (fragmentCode) {

            LIST_MOVIE_FRAGMENT_TAG -> {
                fragmentManager.findFragmentByTag(LIST_MOVIE_FRAGMENT_TAG)
                        ?: ListMovieFragment.newInstance()
            }
            DETAIL_FRAGMENT_TAG -> DetailFragment.newInstance(idMovie)
            else -> null
        }
        fragment?.let { fragmentToReplace ->
            if (fragmentToReplace.isVisible.not()) {
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.content_fragment, fragmentToReplace, fragmentCode)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }


}
