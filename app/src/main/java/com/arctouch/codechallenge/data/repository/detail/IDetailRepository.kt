package com.arctouch.codechallenge.data.repository.detail

import com.arctouch.codechallenge.data.model.Movie
import io.reactivex.Observable

interface IDetailRepository {
    fun getMovieById(id: Long): Observable<Movie>
}