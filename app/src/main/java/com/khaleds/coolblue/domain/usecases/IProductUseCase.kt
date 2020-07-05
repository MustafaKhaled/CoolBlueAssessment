package com.khaleds.coolblue.domain.usecases

import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.repo.ProductsRepository

interface IProductUseCase {
  suspend  fun getAllProducts(): ProductsResponse
}