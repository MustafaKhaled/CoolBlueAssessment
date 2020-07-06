package com.khaleds.coolblue.data.remote.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewSummary(
    val reviewAverage: Double?,
    val reviewCount: Int?
) : Parcelable