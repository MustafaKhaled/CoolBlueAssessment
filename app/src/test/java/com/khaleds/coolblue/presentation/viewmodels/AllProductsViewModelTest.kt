package com.khaleds.coolblue.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.usecases.allproducts.IProductUseCase
import com.khaleds.coolblue.util.StateUi
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class AllProductsViewModelTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var allProductsUseCase: IProductUseCase
    @Mock
    lateinit var viewState: Observer<StateUi>
    lateinit var viewModel: AllProductsViewModel
    private val receivedUiStates: MutableList<StateUi> =
        arrayListOf()
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = AllProductsViewModel(allProductsUseCase)
        viewModel.uiState().observeForever(viewState)
    }

    private fun observeViewModel(viewModel: AllProductsViewModel) {
        viewModel.uiState.observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }

    @Test
    fun `execute getAllProducts and success state result`(){
        runBlockingTest {
            val result = ProductsResponse(1,1,10, listOf(),10)
            Mockito.`when`(allProductsUseCase.getAllProducts()).thenReturn(result)
            viewModel.getAllProducts()
            verify(viewState).onChanged(StateUi.Loading)
            verify(viewState).onChanged(StateUi.Success(result))
        }
    }

}