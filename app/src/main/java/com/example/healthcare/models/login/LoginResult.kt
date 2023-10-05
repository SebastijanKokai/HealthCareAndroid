package com.example.healthcare.models.login

import com.example.healthcare.models.UserData

data class LoginResult(
    val data: UserData?,
    val errorMessage: String?
)



