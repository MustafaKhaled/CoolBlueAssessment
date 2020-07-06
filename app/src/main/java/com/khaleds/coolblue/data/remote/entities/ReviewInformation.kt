package com.khaleds.coolblue.data.remote.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewInformation(
    val reviewSummary: ReviewSummary?,
    val reviews: List<String>?
) : Parcelable