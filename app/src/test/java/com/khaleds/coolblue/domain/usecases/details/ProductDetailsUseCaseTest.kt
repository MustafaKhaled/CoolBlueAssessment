package com.khaleds.coolblue.domain.usecases.details

import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.repo.ProductsRepository
import com.khaleds.coolblue.domain.usecases.allproducts.AllProductsUseCase
import com.khaleds.coolblue.util.TestCoroutineRule
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.*

@ExperimentalCoroutinesApi
class ProductDetailsUseCaseTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    private lateinit var useCase: ProductDetailsUseCase
    @Mock
    private lateinit var iProductsUseCase:IProductDetailsUseCase
    @Mock
    private lateinit var allProductsUseCase: AllProductsUseCase
    @Mock
    private lateinit var productsRepository: ProductsRepository
    @Mock
    private lateinit var productDetailsResponse: ProductDetailsResponse
    @Captor
    private lateinit var idCaptor: ArgumentCaptor<Int>
    private val productsResponse = ProductsResponse(1,2,5, listOf(),10)
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = ProductDetailsUseCase(productsRepository)
//        allProductsUseCase = AllProductsUseCase(productsRepository)
    }

    @Test
    fun `call getProductDetails() and verify repo called successfully`() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(productsRepository.getProductDetails(1)).thenReturn(productDetailsResponse)
            val result = useCase.getDetails(1)
            verify(productsRepository, times(1)).getProductDetails(1)
            assertEquals(result,productDetailsResponse)
        }
    }

    @Test
    fun `call getProductDetails() and verify no interaction for getAllProducts with success`(){
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(productsRepository.getProductDetails(1)).thenReturn(productDetailsResponse)
            useCase.getDetails(1)
            verifyZeroInteractions(allProductsUseCase)
        }
    }

    @Test
    fun `capture the Id sent by parameter with success`() {
        runBlockingTest {
            iProductsUseCase.getDetails(1)
            verify(iProductsUseCase).getDetails(idCaptor.capture())
            val cap = idCaptor.value
            assertEquals(cap, 1)

        }
    }

    @Test
    fun `capture the Id sent by parameter with failure`() {
        runBlockingTest {
            iProductsUseCase.getDetails(1)
            verify(iProductsUseCase).getDetails(idCaptor.capture())
            val cap = idCaptor.value

            assertNotEquals(cap, 2)

        }
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun `execute ProductDetails by Id and return null if exception thrown`() {
        runBlockingTest {
            whenever(iProductsUseCase.getDetails(1)).thenThrow(RuntimeException::class.java)
            iProductsUseCase.getDetails(1)
            assertNull(iProductsUseCase.getDetails(1))
        }
    }
}