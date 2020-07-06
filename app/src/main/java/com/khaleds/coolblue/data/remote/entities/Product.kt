package com.khaleds.coolblue.data.remote.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("USPs")
    val USPs: List<String>?,
    @SerializedName("availabilityState")
    val availabilityState: Int?,
    @SerializedName("cons")
    val cons: List<String>?,
    @SerializedName("deliveredWith")
    val deliveredWith: List<String>?,
    @SerializedName("nextDayDelivery")
    val nextDayDelivery: Boolean?,
    @SerializedName("productId")
    val productId: Int?,
    @SerializedName("productImage")
    val productImage: String?,
    @SerializedName("productImages")
    val productImages: List<String>?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("productText")
    val productText: String?,
    @SerializedName("pros")
    val pros: List<String>?,
    @SerializedName("recommendedAccessories")
    val recommendedAccessories: List<Int>?,
    @SerializedName("reviewInformation")
    val reviewInformation: ReviewInformation?,
    @SerializedName("salesPriceExVat")
    val salesPriceExVat: Double?,
    @SerializedName("salesPriceIncVat")
    val salesPriceIncVat: Double?,
    @SerializedName("specificationSummary")
    val specificationSummary: List<SpecificationSummary>?
) : Parcelable