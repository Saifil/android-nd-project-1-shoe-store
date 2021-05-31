package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.User

class ShoeListViewModelFactory(private val user: User?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeListViewModel::class.java)) {
            return ShoeListViewModel(user = user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}