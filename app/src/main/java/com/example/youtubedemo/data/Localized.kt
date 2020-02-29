package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Localized(
    val description: String,
    val title: String
) : Parcelable