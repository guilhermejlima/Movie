package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.data.repository.home.HomeService
import com.arctouch.codechallenge.util.Constants.URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


val serviceModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
    single { createHomeService(get()) }

}

fun createOkHttpClient(): OkHttpClient {
    val interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
                .addHeader("Accept","application/json, text/plain, */*")
                .build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
}


fun createRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun createHomeService(retrofit: Retrofit) : HomeService = retrofit.create(HomeService::class.java)