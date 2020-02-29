package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentDetails(
    val caption: String?,
    val definition: String?,
    val dimension: String?,
    val duration: String?,
    val licensedContent: Boolean?,
    val projection: String?,
    val regionRestriction: RegionRestriction?
) : Parcelable