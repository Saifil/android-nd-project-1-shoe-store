package com.udacity.shoestore.screens.shoedetail

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.ShoeDetailFormStaticInfo
import com.udacity.shoestore.utils.hideKeyboard

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var baseViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set the fragment binding and return the root layout
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false)

        viewModel = ViewModelProviders.of(this).get(ShoeDetailViewModel::class.java)

        // hook-up the data binding variables
        with(binding) {
            shoeDetailViewModel = viewModel
            formStaticInfo = ShoeDetailFormStaticInfo()
        }
        baseViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        // set the viewModel observers
        setViewModelObservers()

        return binding.root
    }

    private fun setViewModelObservers() = with(viewModel) {
        // send a toast if user entered invalid fields
        inValidInputMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (!TextUtils.isEmpty(errorMessage)) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        })

        hasAddedShoeDetail.observe(viewLifecycleOwner, Observer { hasAddedShoe ->
            if (hasAddedShoe) {
                // add the shoe to baseViewModel shoeList
                baseViewModel.addNewShoe(shoe)
                // hide keyboard
                this@ShoeDetailFragment.hideKeyboard()
                // navigate back to ShoeList
                findNavController().navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragment4ToShoeListFragment4())
            }
        })
    }
}
