package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistics(
    val commentCount: String?,
    val dislikeCount: String?,
    val favoriteCount: String?,
    val likeCount: String?,
    val viewCount: String?
) : Parcelable