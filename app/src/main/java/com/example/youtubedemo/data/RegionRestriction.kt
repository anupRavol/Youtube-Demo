package com.example.youtubedemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionRestriction(
    val allowed: List<String>?
) : Parcelable