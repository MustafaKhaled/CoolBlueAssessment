package com.khaleds.coolblue.domain.usecases.allproducts

import com.khaleds.coolblue.data.repo.ProductsRepositoryImpl
import com.khaleds.coolblue.domain.repo.ProductsRepository
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AllProductsUseCaseTest {
    @Mock private lateinit var productsRepositoryImpl: ProductsRepositoryImpl
    lateinit var allProductsUseCase: AllProductsUseCase
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        allProductsUseCase = AllProductsUseCase(productsRepositoryImpl)
    }

    @Test
    fun getAllProducts() {
//        whenever(productsRepositoryImpl.)
    }
}