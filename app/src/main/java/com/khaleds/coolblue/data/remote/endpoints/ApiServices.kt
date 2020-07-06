package com.khaleds.coolblue.data.remote.endpoints

import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("search?query=apple&page=1")
    suspend fun getProducts(): ProductsResponse

    @GET("product/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): ProductDetailsResponse


}