package com.example.healthcare.models.login

import com.example.healthcare.models.user.UserData

data class LoginResult(
    val data: UserData?,
    val errorMessage: String?
)



