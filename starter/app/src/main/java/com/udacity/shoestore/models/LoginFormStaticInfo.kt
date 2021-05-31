package com.udacity.shoestore.models

data class LoginFormStaticInfo(
    val emailLabel: String = "Email",
    val emailHint: String = "Enter your email...",
    val passwordLabel: String = "Password",
    val passwordHint: String = "Enter your existing or new password...",
    val loginButtonText: String = "Login",
    val signUpButtonText: String = "SignUp"
)