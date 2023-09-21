package com.example.healthcare.models.login

data class LoginRequest(
    var username: String = "",
    var password: String = ""
) {
    fun isNotEmpty(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}