package com.example.healthcare.models

data class Credentials(
    var username: String = "",
    var password: String = ""
) {
    fun isNotEmpty(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}