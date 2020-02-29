package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val contentDetails: ContentDetails?,
    val etag: String?,
    val id: String?,
    val kind: String?,
    val snippet: Snippet?,
    val statistics: Statistics?
) : Parcelable