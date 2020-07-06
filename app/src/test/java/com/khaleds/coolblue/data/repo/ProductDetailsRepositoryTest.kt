package com.khaleds.coolblue.data.repo

import com.khaleds.coolblue.data.remote.endpoints.ApiServices
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.repo.ProductsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.*

@ExperimentalCoroutinesApi
class ProductDetailsRepositoryTest{
    @Mock
    lateinit var repository: ProductsRepository
    lateinit var productsImpl : ProductsRepositoryImpl
    @Mock
    private lateinit var productsResponse: ProductsResponse
    @Mock
    private lateinit var mockServiceApi: ApiServices
    @Mock
    private lateinit var productDetailsResponse: ProductDetailsResponse
    @Captor
    private lateinit var idCaptor: ArgumentCaptor<Int>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productsImpl = ProductsRepositoryImpl(mockServiceApi)
    }

    @Test
    fun `execute getProductDetails() and get result success`() {
        runBlockingTest {
            Mockito.`when`(mockServiceApi.getProductDetails(1)).thenReturn(productDetailsResponse)
            val result = productsImpl.getProductDetails(1)
            verify(mockServiceApi, atLeastOnce()).getProductDetails(1)
            assertEquals(result,productDetailsResponse)
        }
    }

    @Test
    fun `execute getProductDetails() and check zero interaction in AllProducts()`(){
        runBlockingTest {
            Mockito.`when`(mockServiceApi.getProducts()).thenReturn(productsResponse)
            productsImpl.getProductDetails(1)
            verifyZeroInteractions(mockServiceApi.getProducts())
        }
    }

    @Test fun `capture the Id sent by parameter`(){
        runBlockingTest {
            repository.getProductDetails(1)
            verify(repository).getProductDetails(idCaptor.capture())
            val cap = idCaptor.value

            assertEquals(cap,1)

        }
    }
}