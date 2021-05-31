package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class MainViewModel : ViewModel() {

    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn
    val shoesList: LiveData<ArrayList<Shoe>>
        get() = _shoesList

    /** Internal usage only **/
    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    private val _shoesList = MutableLiveData<ArrayList<Shoe>>()
    private val _user = MutableLiveData<User>()

    init {
        // when the app is launched, set user login to false
        // as we don't save any user data once the app is closed
        setUserData() // set null user to denote user loggedOut state
        resetShoeList()
    }

    fun notifyUserLoggedOut() {
        _isUserLoggedIn.value = false
        _user.value = null
    }

    fun setUserData(user: User? = null) {
        _user.value = user
        _isUserLoggedIn.value = _user.value != null
    }

    /** internal helper functions**/
    private fun resetShoeList() {
        _shoesList.value = arrayListOf()
    }
}