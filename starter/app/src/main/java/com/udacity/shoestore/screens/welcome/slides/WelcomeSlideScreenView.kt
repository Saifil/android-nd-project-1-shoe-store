package com.udacity.shoestore.screens.welcome.slides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeSlidesViewBinding
import com.udacity.shoestore.models.WelcomeSlide

class WelcomeSlideScreenView(
    private val welcomeSlide: WelcomeSlide
) : Fragment() {

    private lateinit var binding: WelcomeSlidesViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set the fragment binding and return the root layout
        binding = DataBindingUtil.inflate(
            inflater, R.layout.welcome_slides_view, container, false)

        // setup the slides
        setup()

        return binding.root
    }

    private fun setup() = with(binding) {
        slideLogo.setImageResource(welcomeSlide.imageResId)
        slideTitleText.text = welcomeSlide.title
        slideSubtitleText.text = welcomeSlide.subtitle
    }
}