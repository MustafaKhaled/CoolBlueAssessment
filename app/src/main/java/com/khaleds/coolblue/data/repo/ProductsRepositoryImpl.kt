package com.khaleds.coolblue.data.repo

import com.khaleds.coolblue.data.remote.endpoints.ApiServices
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.repo.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val apiServices: ApiServices): ProductsRepository {
    override suspend fun getAllProducts(): ProductsResponse {
        return apiServices.getProducts()
    }

    override suspend fun getProductDetails( id: Int): Product {
        return apiServices.getProductDetails(id)
    }
}