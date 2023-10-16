package com.example.healthcare.models.register

import com.example.healthcare.models.user.UserData

data class RegisterResult(
    val data: UserData?,
    val errorMessage: String?
)