package com.khaleds.coolblue.data.remote.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PromoIcon(
    val text: String?,
    val type: String?
) : Parcelable