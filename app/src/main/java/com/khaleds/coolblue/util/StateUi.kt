package com.khaleds.coolblue.util

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

sealed class StateUi {
    data class Success(val data: Any) : StateUi()
    object Loading : StateUi()
    class Error(val throwable: Throwable) : StateUi()
}