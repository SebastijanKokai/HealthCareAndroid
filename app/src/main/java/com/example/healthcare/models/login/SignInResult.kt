package com.example.healthcare.models.login

import com.example.healthcare.models.UserData

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)



