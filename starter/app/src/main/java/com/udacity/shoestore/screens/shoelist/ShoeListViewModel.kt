package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {
    val shouldNavigateToShoeDetailScreen: LiveData<Boolean>
        get() = _shouldNavigateToShoeDetailScreen

    /** Internal usage only **/
    private val _shouldNavigateToShoeDetailScreen = MutableLiveData<Boolean>()

    init {
        _shouldNavigateToShoeDetailScreen.value = false
    }

    fun onFabNavButtonClicked() {
        _shouldNavigateToShoeDetailScreen.value = true
        onShoeDetailScreenNavigated()
    }

    /** internal helper functions**/
    private fun onShoeDetailScreenNavigated() {
        _shouldNavigateToShoeDetailScreen. value = false
    }
}