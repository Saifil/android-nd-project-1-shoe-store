package com.udacity.shoestore.models

import com.udacity.shoestore.R

data class WelcomeSlide(
    val imageResId: Int,
    val title: String,
    val subtitle: String
)

fun getDefaultSlides() = arrayListOf(
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