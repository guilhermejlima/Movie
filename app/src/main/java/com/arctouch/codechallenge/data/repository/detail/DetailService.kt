package com.arctouch.codechallenge.data.repository.detail

import com.arctouch.codechallenge.data.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailService {

    @GET("movie/{id}")
    fun getMovieById(
            @Path("id") id: Long,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Observable<Movie>
}