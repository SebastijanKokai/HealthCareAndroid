package com.example.healthcare.models.register

import com.example.healthcare.models.UserData

data class RegisterResult(
    val data: UserData?,
    val errorMessage: String?
)