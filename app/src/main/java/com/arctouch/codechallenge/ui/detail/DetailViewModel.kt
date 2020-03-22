package com.arctouch.codechallenge.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.data.repository.detail.DetailRepository
import com.arctouch.codechallenge.data.repository.detail.IDetailRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val detailRepository: IDetailRepository) : ViewModel() {

    private val movieLiveData by lazy {
        MutableLiveData<Movie>()
    }

    @SuppressLint("CheckResult")
    fun getMovie(id: Long): LiveData<Movie>{
        detailRepository.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    movieLiveData.postValue(it)
                }
        return movieLiveData
    }
}

