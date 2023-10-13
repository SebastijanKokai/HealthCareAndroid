package com.example.healthcare.models.register

data class RegisterState(
    val isRegisterSuccessful: Boolean = false,
    val registerError: String? = null
)