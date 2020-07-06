package com.khaleds.coolblue.data.repo

import com.khaleds.coolblue.data.remote.endpoints.ApiServices
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.repo.ProductsRepository
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.*
import java.net.UnknownHostException
import kotlin.RuntimeException

@ExperimentalCoroutinesApi
class ProductDetailsRepositoryTest {
    @Mock
    lateinit var repository: ProductsRepository
    lateinit var productsImpl: ProductsRepositoryImpl

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
            assertEquals(result, productDetailsResponse)
        }
    }

    @Test
    fun `execute getProductDetails() and check zero interaction in AllProducts()`() {
        runBlockingTest {
            Mockito.`when`(mockServiceApi.getProducts()).thenReturn(productsResponse)
            productsImpl.getProductDetails(1)
            verifyZeroInteractions(mockServiceApi.getProducts())
        }
    }

    @Test
    fun `capture the Id sent by parameter with success`() {
        runBlockingTest {
            repository.getProductDetails(1)
            verify(repository).getProductDetails(idCaptor.capture())
            val cap = idCaptor.value

            assertEquals(cap, 1)

        }
    }

    @Test
    fun `capture the Id sent by parameter with failure`() {
        runBlockingTest {
            repository.getProductDetails(1)
            verify(repository).getProductDetails(idCaptor.capture())
            val cap = idCaptor.value

            assertNotEquals(cap, 2)

        }
    }


    @Test
    fun `execute ProductDetails by Id and return null if exception thrown`() {
        runBlockingTest {
            whenever(productsImpl.getProductDetails(1)).thenThrow(RuntimeException::class.java)
            repository.getProductDetails(1)
            assertNull(repository.getProductDetails(1))
        }
    }
}