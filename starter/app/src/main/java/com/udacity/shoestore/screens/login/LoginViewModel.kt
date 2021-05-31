package com.udacity.shoestore.screens.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class LoginViewModel: ViewModel() {

    val inValidInputMessage: LiveData<String>
        get() = _inValidInputMessage
    val hasSuccessfullyLoggedIn: LiveData<Boolean>
        get() = _hasLoggedIn

    /** Internal usage only **/
    private val _inValidInputMessage = MutableLiveData<String>()
    private val _hasLoggedIn = MutableLiveData<Boolean>()

    // populated directly from the layout using 2-way data binding
    // downside: can be modified from outside the viewModel
    val user: User = User()

    init {
        _inValidInputMessage.value = ""
        _hasLoggedIn.value = false
    }

    fun onPressedLogin() = if (isValidUserInput()) {
        _hasLoggedIn.value = true
        onLoggedIn()
    } else {
        // reset the invalid input message
        onInvalidMessageShown()
    }

    fun onPressedSignup() {
        onPressedLogin()
    }

    /** internal helper functions**/
    private fun CharSequence?.isValidEmail(): Boolean = this?.let { input ->
        Patterns.EMAIL_ADDRESS.matcher(input).matches()
    } ?: false

    private fun isValidUserInput(): Boolean {
        _inValidInputMessage.value = if (TextUtils.isEmpty(user.email) || TextUtils.isEmpty(user.password)) {
            "Please enter the Email & Password"
        } else if (!user.email.isValidEmail()) {
            "Please enter a valid Email address"
        } else {
            ""
        }
        return TextUtils.isEmpty(_inValidInputMessage.value)
    }

    private fun onInvalidMessageShown() {
        _inValidInputMessage.value = ""
    }

    private fun onLoggedIn() {
        _hasLoggedIn.value = false
    }
}