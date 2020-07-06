package com.khaleds.coolblue.domain.usecases.details

import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse

interface IProductDetailsUseCase {
    suspend fun getDetails(id: Int): ProductDetailsResponse
}