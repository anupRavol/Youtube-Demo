package com.example.youtubedemo.di

import com.example.youtubedemo.NetworkRepository
import com.example.youtubedemo.YouTubeAPI
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(repository: NetworkRepository)
}