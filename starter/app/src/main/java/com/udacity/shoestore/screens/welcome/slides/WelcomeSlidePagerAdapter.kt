package com.udacity.shoestore.screens.welcome.slides

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.udacity.shoestore.models.WelcomeSlide

class WelcomeSlidePagerAdapter(
    fragment: Fragment,
    private val slidePageList: ArrayList<WelcomeSlide>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = slidePageList.size

    private fun getWelcomeSlide(position: Int): WelcomeSlide = slidePageList[position]

    override fun createFragment(position: Int): Fragment =
        WelcomeSlideScreenView(
            getWelcomeSlide(position)
        )
}