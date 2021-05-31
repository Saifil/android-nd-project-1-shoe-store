package com.udacity.shoestore.screens.instruction

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
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    private lateinit var viewModel: InstructionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set the fragment binding and return the root layout
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instruction, container, false)

        viewModel = ViewModelProviders.of(this).get(InstructionViewModel::class.java)

        // attach data binding to viewModel
        binding.instructionViewModel = viewModel

        // set the viewModel observers
        setViewModelObservers()

        return binding.root
    }

    private fun setViewModelObservers() = with(viewModel) {
        shouldNavigateToShoeListScreen.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(
                    InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment4())
            }
        })
    }
}
