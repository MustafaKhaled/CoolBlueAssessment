package com.khaleds.coolblue.data.remote.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val cons: List<String>?,
    val creationDate: String?,
    val creatorName: String?,
    val description: String?,
    val languageCode: String?,
    val pros: List<String>?,
    val rating: Double?,
    val reviewId: Int?,
    val title: String?
) : Parcelable