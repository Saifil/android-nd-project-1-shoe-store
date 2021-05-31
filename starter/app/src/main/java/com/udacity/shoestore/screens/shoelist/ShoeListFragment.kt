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
import com.udacity.shoestore.utils.visibleIf

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private lateinit var viewModel: ShoeListViewModel
    private lateinit var baseViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set the fragment binding and return the root layout
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false)

        viewModel = ViewModelProviders.of(this).get(ShoeListViewModel::class.java)
        // fetch the base viewModel from ActivityMain
        baseViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        // attach data binding to viewModel
        binding.shoeListViewModel = viewModel

        // set the viewModel observers
        setViewModelObservers()

        // if no shoes to show, show the empty view
        baseViewModel.shoesList.value?.let { handleEmptyViewState(it) }

        // set the logout menu
        setupToolbar()

        binding.shoeContainer.addView(ShoeItemView(requireContext()))

        return binding.root
    }

    // Toolbar for per-fragment based appbar
    private fun setupToolbar() = with(binding) {
        toolbarLogout.inflateMenu(R.menu.logout_menu)
        toolbarLogout.title = getString(R.string.shoe_list)
        toolbarLogout.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_logout_item -> {
                    baseViewModel.notifyUserLoggedOut()
                    true
                }
                else -> false
            }
        }
    }

    private fun setViewModelObservers() {
        baseViewModel.isUserLoggedIn.observe(viewLifecycleOwner, Observer { isLoggedIn ->
            if (!isLoggedIn) {
                // clear the shoe list
                // here, the ideal behavior would be to save the shoeList if user is an existing
                // user, but as we do not keep a persistent state, we will clear the list for simplicity
                baseViewModel.clearShoeList()
                // If not logged it, navigate to the login screen
                findNavController().navigate(
                    ShoeListFragmentDirections.actionShoeListFragment4ToLoginFragment2())
            }
        })
        baseViewModel.shoesList.observe(viewLifecycleOwner, Observer { currentShoeList ->
            resetShoeListContainer(currentShoeList)
        })

        // listen to navigation event
        viewModel.shouldNavigateToShoeDetailScreen.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(
                    ShoeListFragmentDirections.actionShoeListFragment4ToShoeDetailFragment4())
            }
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

    private fun <T: Any> handleEmptyViewState(currentList: ArrayList<T>) = with(binding) {
        // show the emptyView if necessary
        emptyViewImage.visibleIf(currentList.isNullOrEmpty())
        emptyViewText.visibleIf(currentList.isNullOrEmpty())
    }
}
