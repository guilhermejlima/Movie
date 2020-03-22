package com.arctouch.codechallenge.util

import com.arctouch.codechallenge.util.Constants.BACKDROP_URL
import com.arctouch.codechallenge.util.Constants.POSTER_URL

fun String.buildPosterUrl(): String{
    return "$POSTER_URL$this?api_key=${Constants.API_KEY}"
}

fun String.buildBackdropUrl(): String {
    return "$BACKDROP_URL$this?api_key=${Constants.API_KEY}"
}