package com.example.healthcare.models.login

data class LoginState(
    val isLoginSuccessful: Boolean = false,
    val loginError: String? = null
)
