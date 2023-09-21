package com.example.healthcare.sign_in

import com.example.healthcare.models.UserData

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)



