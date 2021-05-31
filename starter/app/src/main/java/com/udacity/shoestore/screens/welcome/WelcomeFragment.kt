package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.User
import com.udacity.shoestore.screens.welcome.slides.WelcomeSlidePagerAdapter

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var viewModelFactory: WelcomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set the fragment binding and return the root layout
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)


        // get the user status
        val user = getUserFromArgs()
        viewModelFactory = WelcomeViewModelFactory(user)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WelcomeViewModel::class.java)

        // attach data binding to viewModel
        binding.welcomeViewModel = viewModel
        // set this fragment as lifeCycleOwner so
        // the data binding can listen to changes directly
        binding.lifecycleOwner = this

        // setup the viewPager for welcome slides
        setupWelcomeSlides()

        // set the viewModel observers
        setViewModelObservers()

        return binding.root
    }

    private fun getUserFromArgs() : User? {
        // fetch the user arg to check if the user is logged in
        val args = arguments?.let { WelcomeFragmentArgs.fromBundle(it) }
        return args?.userData
    }

    private fun setupWelcomeSlides() = with(binding) {
        viewModel.welcomeSlidesList.value?.let { _welcomeSlides ->
            // setup only if welcomeSlidesList is non-null
            slideViewPager.adapter =
                WelcomeSlidePagerAdapter(
                    fragment = this@WelcomeFragment,
                    slidePageList = _welcomeSlides
                )
            // disable swiping as we will use the button to guide user
            slideViewPager.isUserInputEnabled = false
        }
    }

    private fun setViewModelObservers() = with(viewModel) {
        currentSlideNum.observe(viewLifecycleOwner, Observer { currentSlideNum ->
            if (binding.slideViewPager.adapter != null) {
                binding.slideViewPager.currentItem = currentSlideNum
            }
        })
        shouldNavigateToInfoScreen.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(
                    WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment(user))
            }
        })
    }
}
