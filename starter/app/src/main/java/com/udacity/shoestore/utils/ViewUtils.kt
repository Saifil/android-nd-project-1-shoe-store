package com.udacity.shoestore.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun Fragment?.hideKeyboard() = this?.let { _ ->
    activity?.currentFocus?.let { currentFocus ->
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(currentFocus.windowToken,0)
    }
}