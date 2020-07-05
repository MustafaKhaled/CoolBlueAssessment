package com.khaleds.coolblue.data.remote.endpoints

import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("ios-assignment/search?query=apple&page=1")
    suspend fun getProducts(): ProductsResponse

    @GET("ios-assignment/product/{id}")
    suspend fun getProductDetails(@Path("id") id: Int)


}