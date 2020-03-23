package com.arctouch.codechallenge.data.repository.detail

import com.arctouch.codechallenge.data.model.Movie
import com.arctouch.codechallenge.util.Constants.API_KEY
import com.arctouch.codechallenge.util.Constants.DEFAULT_LANGUAGE
import io.reactivex.Observable

class DetailRepository(private val service: DetailService): IDetailRepository {

    override fun getMovieById(id: Long): Observable<Movie> =
        service.getMovieById(id,API_KEY, DEFAULT_LANGUAGE)

}