package com.arctouch.codechallenge.data.repository.home

import com.arctouch.codechallenge.model.GenreResponse
import com.arctouch.codechallenge.model.UpcomingMoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("movie/upcoming")
    fun upcomingMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Long,
            @Query("region") region: String
    ): Observable<UpcomingMoviesResponse>

    @GET("genre/movie/list")
    fun genres(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Observable<GenreResponse>

}