package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private lateinit var viewModel: ShoeListViewModel
    private lateinit var viewModelFactory: ShoeListViewModelFactory
    private lateinit var baseViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set the fragment binding and return the root layout
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false)

        val user = getUserFromArgs()
        // delegate user login state check to viewModel
        viewModelFactory = ShoeListViewModelFactory(user)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoeListViewModel::class.java)

        // fetch the base viewModel from ActivityMain
        baseViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        // set the viewModel observers
        setViewModelObservers()

        // set the logout menu
        setupToolbar()

        binding.shoeContainer.addView(ShoeItemView(requireContext()))

        return binding.root
    }

    // Toolbar for per-fragment based appbar
    private fun setupToolbar() = with(binding) {
        toolbarLogout.inflateMenu(R.menu.logout_menu)
        toolbarLogout.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_logout_item -> {
                    viewModel.notifyUserLoggedOut()
                    true
                }
                else -> false
            }
        }
    }

    private fun getUserFromArgs(): User? {
        // fetch the user arg to check if the user is logged in
        val args = arguments?.let { ShoeListFragmentArgs.fromBundle(it) }
        return args?.userData
    }

    private fun setViewModelObservers() {
        viewModel.isUserLoggedIn.observe(viewLifecycleOwner, Observer { isLoggedIn ->
            if (!isLoggedIn) {
                // If not logged it, navigate to the login screen
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragment4ToLoginFragment2())
            }
        })
        baseViewModel.shoesList.observe(viewLifecycleOwner, Observer { currentShoeList ->
            resetShoeListContainer(currentShoeList)
        })
    }

    private fun resetShoeListContainer(shoeList: ArrayList<Shoe>) = with(binding) {
        // remove all the current children
        shoeContainer.removeAllViews()
        // add all the show views to the container
        shoeList.forEach { shoe ->
            shoeContainer.addView(
                ShoeItemView(requireContext()).apply { setup(shoe) }
            )
        }
    }
}
