package com.arctouch.codechallenge.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.data.repository.home.IHomeRepository
import com.arctouch.codechallenge.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val homeRepository: IHomeRepository):ViewModel() {


    private val listMoviesLiveData by lazy {
        MutableLiveData<List<Movie>>()
    }

    fun getUpcomingMovies(): LiveData<List<Movie>>{
        return listMoviesLiveData
    }

    @SuppressLint("CheckResult")
    fun updateList(){
        homeRepository.getUpcomingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val moviesWithGenres = it.results.map { movie ->
                        movie.copy(genres = Cache.genres.filter { movie.genreIds?.contains(it.id) == true })
                    }
                    listMoviesLiveData.postValue(moviesWithGenres)

                }
    }

    @SuppressLint("CheckResult")
    fun updateGenres(){
        homeRepository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Cache.cacheGenres(it.genres)
                }
    }


}