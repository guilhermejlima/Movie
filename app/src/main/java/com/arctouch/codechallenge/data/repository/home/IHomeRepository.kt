package com.arctouch.codechallenge.data.repository.home

import com.arctouch.codechallenge.model.GenreResponse
import com.arctouch.codechallenge.model.UpcomingMoviesResponse
import io.reactivex.Observable

interface IHomeRepository {
    fun getUpcomingMovies(): Observable<UpcomingMoviesResponse>
    fun getGenres(): Observable<GenreResponse>
}