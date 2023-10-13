package com.example.healthcare.models.login

import com.example.healthcare.extension.isEmailValid
import com.example.healthcare.extension.isPasswordValid

data class LoginRequest(
    var email: String = "",
    var password: String = ""
) {
    fun isValid() : Boolean {
        return email.isEmailValid() && password.isPasswordValid()
    }

    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}