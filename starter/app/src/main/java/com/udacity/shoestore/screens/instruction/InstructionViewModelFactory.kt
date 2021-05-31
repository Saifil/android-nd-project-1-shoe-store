package com.udacity.shoestore.screens.instruction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.User

class InstructionViewModelFactory(private val user: User?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InstructionViewModel::class.java)) {
            return InstructionViewModel(user = user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}