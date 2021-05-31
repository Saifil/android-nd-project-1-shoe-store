package com.udacity.shoestore.screens.shoedetail

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.getEmptyShoe

class ShoeDetailViewModel : ViewModel() {

    val inValidInputMessage: LiveData<String>
        get() = _inValidInputMessage
    val hasAddedShoeDetail: LiveData<Boolean>
        get() = _hasAddedShoeDetail
    // see if user has actioned to navigate back to shoeList page
    val hasIntentNavigateUp: LiveData<Boolean>
        get() = _hasIntentNavigateUp

    /** Internal usage only **/
    private val _inValidInputMessage = MutableLiveData<String>()
    private val _hasAddedShoeDetail = MutableLiveData<Boolean>()
    private val _hasIntentNavigateUp = MutableLiveData<Boolean>()

    // populated directly from the layout using 2-way data binding
    // downside: can be modified from outside the viewModel
    val shoe: Shoe = getEmptyShoe()

    init {
        _inValidInputMessage.value = ""
        _hasAddedShoeDetail.value = false
        _hasIntentNavigateUp.value = false
    }

    fun onClickedSave() = if (isValidShoeInput()) {
        _hasAddedShoeDetail.value = true
        onShoeDetailAdded()
    } else {
        // reset the invalid input message
        onInvalidMessageShown()
    }

    fun onClickedCancel() {
        _hasIntentNavigateUp.value = true
        onNavigatedUp()
    }

    /** internal helper functions**/
    private fun isValidShoeInput(): Boolean {
        _inValidInputMessage.value = if (isAnyFieldEmpty()) {
            "Please check for empty fields or incorrect shoe size"
        } else {
            ""
        }
        return TextUtils.isEmpty(_inValidInputMessage.value)
    }

    private fun isAnyFieldEmpty(): Boolean = with(shoe) {
        arrayListOf(
            name,
            company,
            textSize,
            description
        ).forEach { input: String ->
            if (TextUtils.isEmpty(input)) {
                return true
            }
        }
        return false
    }

    private fun onInvalidMessageShown() {
        _inValidInputMessage.value = ""
    }

    private fun onShoeDetailAdded() {
        _hasAddedShoeDetail.value = false
    }

    private fun onNavigatedUp() {
        _hasIntentNavigateUp.value = false
    }
}