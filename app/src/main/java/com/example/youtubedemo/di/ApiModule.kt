package com.example.youtubedemo.di

import com.example.youtubedemo.Constants
import com.example.youtubedemo.NetworkRepository
import com.example.youtubedemo.YouTubeAPI
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
open class ApiModule {

    @Provides
    fun provideYoutubeAPI(): YouTubeAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(YouTubeAPI::class.java)
    }

    @Provides
    open fun provideYoutubeApiService() : NetworkRepository{
        return NetworkRepository()
    }
}