package com.example.youtubedemo

import com.example.youtubedemo.data.VideoListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface YouTubeAPI {

    @GET("/youtube/v3/videos")
    fun getPopularVideos(@QueryMap(encoded = true) queryMap: Map<String, String>) : Observable<VideoListResponse>
}