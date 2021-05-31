package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class ShoeListViewModel(private val user: User?) : ViewModel() {

    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

    /** Internal usage only **/
    private val _isUserLoggedIn = MutableLiveData<Boolean>()

    init {
        setUserLoggedInState()
    }

    fun notifyUserLoggedOut() {
        _isUserLoggedIn.value = false
    }

    /** internal helper functions**/
    private fun setUserLoggedInState() {
//        _isUserLoggedIn.value = user != null
        _isUserLoggedIn.value = true
    }
}