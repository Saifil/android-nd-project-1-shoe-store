package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {
    val shoesList: LiveData<ArrayList<Shoe>>
        get() = _shoesList

    /** Internal usage only **/
    private var _shoesList = MutableLiveData<ArrayList<Shoe>>()

    init {
        resetShoeList()
    }

    private fun resetShoeList() {
        _shoesList.value = arrayListOf()
    }
}