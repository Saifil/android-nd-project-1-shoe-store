package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.WelcomeSlide
import com.udacity.shoestore.models.getDefaultSlides

private const val BUTTON_NEXT = "Next"
private const val BUTTON_CONTINUE = "Continue"

class WelcomeViewModel : ViewModel() {

    val welcomeSlidesList: LiveData<ArrayList<WelcomeSlide>>
        get() = _welcomeSlidesList
    val currentSlideNum: LiveData<Int>
        get() = _currentSlideNum
    val slideButtonText: LiveData<String>
        get() = _slideButtonText
    val shouldNavigateToInfoScreen: LiveData<Boolean>
        get() = _shouldNavigateToInfoScreen

    /** Internal usage only **/
    private val _welcomeSlidesList = MutableLiveData<ArrayList<WelcomeSlide>>()
    private val _currentSlideNum = MutableLiveData<Int>()
    private val _slideButtonText = MutableLiveData<String>()
    private val _shouldNavigateToInfoScreen = MutableLiveData<Boolean>()

    init {
        setupStaticWelcomeSlides()
        _currentSlideNum.value = 0
        setSlideButtonValue()
        _shouldNavigateToInfoScreen.value = false
    }

    private fun setupStaticWelcomeSlides() {
        _welcomeSlidesList.value = getDefaultSlides()
    }

    fun onSlideNextClicked() = if (hasNoMoreSlides()) {
        _shouldNavigateToInfoScreen.value = true
        onInfoScreenNavigated()
    } else {
        _currentSlideNum.value = _currentSlideNum.value?.plus(1)
        setSlideButtonValue()
    }

    /** internal helper functions**/
    private fun hasNoMoreSlides()
        = currentSlideNum.value == welcomeSlidesList.value?.size?.minus(1)

    private fun setSlideButtonValue() {
        _slideButtonText.value = if (hasNoMoreSlides()) BUTTON_CONTINUE else BUTTON_NEXT
    }

    private fun onInfoScreenNavigated() {
        // set the value back to false as signal was sent
        _shouldNavigateToInfoScreen.value = false
    }
}