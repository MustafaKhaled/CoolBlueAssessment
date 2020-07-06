package com.khaleds.coolblue.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

inline fun View.snack(message: String) {
    val snack = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    snack.action("Ok") {snack.dismiss()}
    snack.show()
}

fun Snackbar.action(action: String, listener: (View) -> Unit) {
    setAction(action, listener)
}