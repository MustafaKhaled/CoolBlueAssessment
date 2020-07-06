package com.khaleds.coolblue.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.khaleds.coolblue.data.remote.entities.Product
import com.khaleds.coolblue.data.remote.entities.ProductDetailsResponse
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.usecases.allproducts.IProductUseCase
import com.khaleds.coolblue.domain.usecases.details.IProductDetailsUseCase
import com.khaleds.coolblue.presentation.di.modules.IProductDetailsUseCaseModule
import com.khaleds.coolblue.util.StateUi
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ProductDetailsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var allProductsUseCase: IProductDetailsUseCase
    @Mock
    lateinit var viewState: Observer<StateUi>
    @Mock
    lateinit var product: Product
    @Mock
    lateinit var viewModel: ProductDetailsViewModel
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = ProductDetailsViewModel(allProductsUseCase)
        viewModel.uiState().observeForever(viewState)
    }

    @Test
    fun `execute getDetails and success state result`(){
        runBlockingTest {
            val result = ProductDetailsResponse(product)
            Mockito.`when`(allProductsUseCase.getDetails(1)).thenReturn(result)
            viewModel.getDetails(1)
            verify(viewState).onChanged(StateUi.Loading)
            verify(viewState).onChanged(StateUi.Success(result))
        }
    }
}