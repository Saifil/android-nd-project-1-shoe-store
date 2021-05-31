package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.User
import com.udacity.shoestore.models.WelcomeSlide

private const val BUTTON_NEXT = "Next"
private const val BUTTON_CONTINUE = "Continue"

class WelcomeViewModel(val user: User?) : ViewModel() {

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
        _welcomeSlidesList.value = arrayListOf(
            WelcomeSlide(
                imageResId = R.drawable.golden_gate,
                title = "Headquartered in San Francisco",
                subtitle = "Made with love by a small team of 5"
            ),
            WelcomeSlide(
                imageResId = R.drawable.global,
                title = "Serving 10+ countries",
                subtitle = "Experience a global taste in shoes"
            ),
            WelcomeSlide(
                imageResId = R.drawable.customer_review,
                title = "Rated 4.8 on Google Play",
                subtitle = "Over 100,000+ reviews in 3 months"
            )
        )
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