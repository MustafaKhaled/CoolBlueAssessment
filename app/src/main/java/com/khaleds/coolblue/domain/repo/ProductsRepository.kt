package com.khaleds.coolblue.domain.repo

import com.khaleds.coolblue.data.remote.entities.ProductsResponse

interface ProductsRepository {
    suspend fun getAllProducts(): ProductsResponse
}