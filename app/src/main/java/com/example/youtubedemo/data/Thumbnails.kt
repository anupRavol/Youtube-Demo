package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnails(
    val default: Default?,
    val high: Default?,
    val maxres: Default?,
    val medium: Default?,
    val standard: Default?
) : Parcelable