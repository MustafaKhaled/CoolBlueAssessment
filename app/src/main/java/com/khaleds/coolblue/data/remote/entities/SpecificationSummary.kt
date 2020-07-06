package com.khaleds.coolblue.data.remote.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpecificationSummary(
    val booleanValue: Boolean?,
    val name: String?,
    val stringValue: String?
) : Parcelable