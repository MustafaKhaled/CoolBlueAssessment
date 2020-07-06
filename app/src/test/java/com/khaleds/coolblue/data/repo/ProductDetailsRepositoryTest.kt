package com.khaleds.coolblue.data.repo

import com.khaleds.coolblue.data.remote.endpoints.ApiServices
import org.junit.Assert.*
import org.mockito.Mock

class ProductDetailsRepositoryTest{
    lateinit var productsImpl : ProductsRepositoryImpl
    @Mock
    private lateinit var mockServiceApi: ApiServices

}