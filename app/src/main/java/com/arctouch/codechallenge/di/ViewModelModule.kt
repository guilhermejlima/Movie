package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.ui.detail.DetailViewModel
import com.arctouch.codechallenge.ui.listMovie.ListMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListMovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}