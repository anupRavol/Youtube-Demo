package com.example.youtubedemo

import com.example.youtubedemo.di.ApiModule

class ApiModuleTest(val mockRepository: NetworkRepository): ApiModule() {

    override fun provideYoutubeApiService():NetworkRepository{
        return mockRepository
    }

}