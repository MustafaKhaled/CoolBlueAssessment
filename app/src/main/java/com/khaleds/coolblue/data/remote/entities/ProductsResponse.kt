package com.khaleds.coolblue.data.remote.entities

data class ProductsResponse(
    val currentPage: Int?,
    val pageCount: Int?,
    val pageSize: Int?,
    val products: List<Product>?,
    val totalResults: Int?
)