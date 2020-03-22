package com.arctouch.codechallenge.data.repository.home

import com.arctouch.codechallenge.data.model.GenreResponse
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import io.reactivex.Observable

interface IHomeRepository {
    fun getUpcomingMovies(): Observable<UpcomingMoviesResponse>
    fun getGenres(): Observable<GenreResponse>
}