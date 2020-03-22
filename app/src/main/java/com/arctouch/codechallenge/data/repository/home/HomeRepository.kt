package com.arctouch.codechallenge.data.repository.home

import com.arctouch.codechallenge.data.model.GenreResponse
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.util.Constants.API_KEY
import com.arctouch.codechallenge.util.Constants.DEFAULT_LANGUAGE
import com.arctouch.codechallenge.util.Constants.DEFAULT_REGION
import io.reactivex.Observable

class HomeRepository(private val service: HomeService): IHomeRepository {

    override fun getUpcomingMovies(): Observable<UpcomingMoviesResponse> =
            service.upcomingMovies(API_KEY, DEFAULT_LANGUAGE, 1, DEFAULT_REGION )

    override fun getGenres(): Observable<GenreResponse> =
        service.genres(API_KEY, DEFAULT_LANGUAGE)

}