package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.User

class WelcomeViewModelFactory(private val user: User?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(user = user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}