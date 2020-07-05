package com.khaleds.coolblue.util

sealed class StateUi {
    data class Success(val data: Any) : StateUi()
    object Loading : StateUi()
    class Error(val throwable: Throwable) : StateUi()
}