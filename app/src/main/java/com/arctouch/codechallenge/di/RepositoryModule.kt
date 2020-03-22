package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.data.repository.detail.DetailRepository
import com.arctouch.codechallenge.data.repository.detail.IDetailRepository
import com.arctouch.codechallenge.data.repository.home.HomeRepository
import com.arctouch.codechallenge.data.repository.home.IHomeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<IHomeRepository> {HomeRepository(get())}
    single<IDetailRepository> {DetailRepository(get())}
}