package com.khaleds.coolblue.domain.usecases.details

import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.domain.repo.ProductsRepository
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(private val productsRepository: ProductsRepository): IProductDetailsUseCase {
    override suspend fun getDetails(id: Int): Product {
        return productsRepository.getProductDetails(id)
    }
}