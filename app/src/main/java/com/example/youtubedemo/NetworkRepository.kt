package com.example.youtubedemo

import com.example.youtubedemo.data.VideoListResponse
import com.example.youtubedemo.di.DaggerApiComponent
import io.reactivex.Observable
import javax.inject.Inject

class NetworkRepository {
    @Inject
    lateinit var api: YouTubeAPI

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPopularPosts(map: HashMap<String, String>): Observable<VideoListResponse> {
        return api.getPopularVideos(map)
    }
}