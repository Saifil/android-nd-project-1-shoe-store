package com.udacity.shoestore.models

data class LoginFormStaticInfo(
    val emailLabel: String = "Email",
    val emailHint: String = "Enter your email...",
    val passwordLabel: String = "Password",
    val passwordHint: String = "Enter your existing or new password...",
    val loginButtonText: String = "Login",
    val signUpButtonText: String = "SignUp"
)

data class ShoeDetailFormStaticInfo(
    val nameLabel: String = "Name",
    val nameHint: String = "Enter name of the shoes",
    val companyLabel: String = "Company",
    val companyHint: String = "Enter Shoe's company name",
    val sizeLabel: String = "Size",
    val sizeHint: String = "Decimal size",
    val descriptionLabel: String = "Description",
    val descriptionHint: String = "Enter a short description",
    val saveButtonText: String = "Save"
)