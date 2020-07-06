package com.khaleds.coolblue.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.khaleds.coolblue.R
import com.khaleds.coolblue.domain.usecases.details.IProductDetailsUseCase
import com.khaleds.coolblue.util.StateUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(private val iProductDetailsUseCase: IProductDetailsUseCase): BaseViewModel<StateUi>() {
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        uiState.postValue(StateUi.Error(exception))
    }

    fun getDetails(id: Int){
        uiState.value = StateUi.Loading
        viewModelScope.launch(Dispatchers.IO +
                exceptionHandler) {
            val result = iProductDetailsUseCase.getDetails(id)
            uiState.postValue(StateUi.Success(result))
        }
    }

    override fun uiState(): LiveData<StateUi> {
        return uiState
    }
}