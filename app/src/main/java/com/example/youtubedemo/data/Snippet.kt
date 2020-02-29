package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Snippet(
    val categoryId: String?,
    val channelId: String?,
    val channelTitle: String?,
    val defaultAudioLanguage: String?,
    val description: String?,
    val liveBroadcastContent: String?,
    val localized: Localized?,
    val publishedAt: String?,
    val tags: List<String>?,
    val thumbnails: Thumbnails?,
    val title: String?
) : Parcelable