package com.udacity.shoestore.screens.login

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
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.LoginFormStaticInfo
import com.udacity.shoestore.utils.hideKeyboard


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // set the fragment binding and return the root layout
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        // attach models to data binding
        binding.apply {
            loginInfoModel = LoginFormStaticInfo()
            loginViewModel = viewModel
        }

        // set the viewModel observers
        setViewModelObservers()

        return binding.root
    }

    private fun setViewModelObservers() = with(viewModel) {
        // setup action for when user tries to login/signup w invalid input
        inValidInputMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (!TextUtils.isEmpty(errorMessage)) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        })

        // user has logged in successfully, thus, redirect them to welcome screen
        hasSuccessfullyLoggedIn.observe(viewLifecycleOwner, Observer { hasLoggedIn ->
            if (hasLoggedIn) {
                this@LoginFragment.hideKeyboard()
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragment2ToWelcomeFragment(user))
            }
        })
    }
}
