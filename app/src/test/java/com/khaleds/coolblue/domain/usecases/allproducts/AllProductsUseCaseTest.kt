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
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
@ExperimentalCoroutinesApi
class AllProductsUseCaseTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Mock private lateinit var productsRepositoryImpl: ProductsRepositoryImpl
    lateinit var allProductsUseCase: AllProductsUseCase
    @Mock private lateinit var productDetailsResponse: ProductsResponse
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        allProductsUseCase = AllProductsUseCase(productsRepositoryImpl)
    }

    @Test
    fun getAllProducts() {
        testCoroutineRule.runBlockingTest {
            whenever(allProductsUseCase.getAllProducts()).thenReturn(productDetailsResponse)
//            allProductsUseCase.getAllProducts()
            verify(productsRepositoryImpl, times(1)).getAllProducts()
        }
    }
}