package com.udacity.shoestore.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun Fragment?.hideKeyboard() = this?.let { _ ->
    activity?.currentFocus?.let { currentFocus ->
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(currentFocus.windowToken,0)
    }
}

fun View.visibleIf(visibilityCondition: Boolean) = if (visibilityCondition) {
    this.visibility = View.VISIBLE
} else {
    this.visibility = View.GONE
}