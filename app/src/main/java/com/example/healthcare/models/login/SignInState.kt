package com.example.healthcare.models.login

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
