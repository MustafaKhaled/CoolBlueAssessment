package com.khaleds.coolblue.domain.repo

import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse

interface ProductsRepository {
    suspend fun getAllProducts(): ProductsResponse
    suspend fun getProductDetails(id: Int): ProductDetailsResponse

}