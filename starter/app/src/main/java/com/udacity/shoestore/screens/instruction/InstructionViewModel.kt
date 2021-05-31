package com.udacity.shoestore.screens.instruction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionViewModel : ViewModel() {
    val title = "Instructions on how to use..."
    val subtitle = "You can scroll through and view all the shoes listing" +
        " on the next page. You can also add a new shoe inventory by clicking the" +
        " + button, which will take you to a form to input the new shoe details." +
        " You can choose to save or cancel the entry for the new shoe. If a new shoe" +
        " is added, it will be reflected at the bottom of the shoe list."
    val navButtonText = "Finish"

    val shouldNavigateToShoeListScreen: LiveData<Boolean>
        get() = _shouldNavigateToShoeListScreen

    /** Internal usage only **/
    private val _shouldNavigateToShoeListScreen = MutableLiveData<Boolean>()

    init {
        _shouldNavigateToShoeListScreen.value = false
    }

    fun onFinishButtonClicked() {
        _shouldNavigateToShoeListScreen.value = true
        onShoeListScreenNavigated()
    }

    /** internal helper functions**/
    private fun onShoeListScreenNavigated() {
        _shouldNavigateToShoeListScreen. value = false
    }
}