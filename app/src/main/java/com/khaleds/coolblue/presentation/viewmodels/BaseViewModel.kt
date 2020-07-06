package com.khaleds.coolblue.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {
    abstract fun uiState(): LiveData<T>
    protected val uiState: MutableLiveData<T> = MutableLiveData()
}
