package com.khaleds.coolblue.domain.usecases.details

import com.khaleds.coolblue.data.remote.entities.Product

interface IProductDetailsUseCase {
    suspend fun getDetails(id: Int): Product
}