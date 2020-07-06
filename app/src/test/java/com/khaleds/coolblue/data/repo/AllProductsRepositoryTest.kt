package com.khaleds.coolblue.data.repo

import com.khaleds.coolblue.data.remote.endpoints.ApiServices
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.repo.ProductsRepository
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class AllProductsRepositoryTest {
    lateinit var productsImpl : ProductsRepositoryImpl
    @Mock
    private lateinit var mockServiceApi: ApiServices
    private val productsResponse = ProductsResponse(1,2,5, listOf(),10)
    @Mock
    private lateinit var productDetailsResponse: ProductDetailsResponse

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productsImpl = ProductsRepositoryImpl(mockServiceApi)
    }

    @Test
    fun `execute getProducts() and get result success`() {
        runBlockingTest {
            `when`(mockServiceApi.getProducts()).thenReturn(productsResponse)
            val result = productsImpl.getAllProducts()
            verify(mockServiceApi, atLeastOnce()).getProducts()
            assertEquals(result,productsResponse)
        }
    }

    @Test
    fun `execute getProducts() and check total result`(){
        runBlockingTest {
            `when`(mockServiceApi.getProducts()).thenReturn(productsResponse)
            val result = productsImpl.getAllProducts()
            verify(mockServiceApi, atLeastOnce()).getProducts()
            assertEquals(result.totalResults,10)
        }
    }


    @Test
    fun `execute getProducts() and check zero interaction in details`(){
        runBlockingTest {
            `when`(mockServiceApi.getProductDetails(1)).thenReturn(productDetailsResponse)
            productsImpl.getAllProducts()
            verifyZeroInteractions(mockServiceApi.getProductDetails(1))
        }
    }

}