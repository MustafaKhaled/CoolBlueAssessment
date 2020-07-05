package com.khaleds.coolblue.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaleds.coolblue.data.remote.entities.ProductsResponse
import com.khaleds.coolblue.domain.usecases.IProductUseCase
import com.khaleds.coolblue.util.StateUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllProductsViewModel @Inject constructor(private val iProductUseCase: IProductUseCase): BaseViewModel<StateUi>() {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        uiState.postValue(StateUi.Error(exception))
    }

    fun getAllProducts(){
        uiState.value = StateUi.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = iProductUseCase.getAllProducts()
            uiState.postValue(StateUi.Success(result))
        }
    }

    fun observeState(): LiveData<StateUi>{
        return uiState()
    }
}