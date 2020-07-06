package com.khaleds.coolblue.domain.usecases.allproducts

import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.repo.ProductsRepository
import javax.inject.Inject

class AllProductsUseCase @Inject constructor(private val productsRepository: ProductsRepository):
    IProductUseCase {
    override suspend fun getAllProducts(): ProductsResponse {
        return productsRepository.getAllProducts()
    }
}