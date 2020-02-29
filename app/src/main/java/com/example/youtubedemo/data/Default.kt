package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Default(
    val height: Int?,
    val url: String?,
    val width: Int?
) : Parcelable