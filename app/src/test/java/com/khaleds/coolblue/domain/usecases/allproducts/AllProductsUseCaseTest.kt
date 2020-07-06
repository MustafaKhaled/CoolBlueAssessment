package com.khaleds.coolblue.domain.usecases.allproducts

import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.data.repo.ProductsRepositoryImpl
import com.khaleds.coolblue.domain.repo.ProductsRepository
import com.khaleds.coolblue.util.TestCoroutineRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
@ExperimentalCoroutinesApi
class AllProductsUseCaseTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var useCase: AllProductsUseCase

    @Mock
    private lateinit var productsRepository: ProductsRepository
    private val productsResponse = ProductsResponse(1,2,5, listOf(),10)


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        useCase = AllProductsUseCase(productsRepository)
    }

    @Test
    fun `call getProducts() and verify repo called successfully`() {
        testCoroutineRule.runBlockingTest {
            `when`(productsRepository.getAllProducts()).thenReturn(productsResponse)
            val result = useCase.getAllProducts()
            verify(productsRepository, times(1)).getAllProducts()
            assertEquals(result,productsResponse)
        }
    }
}