package com.example.healthcare.models.login

import com.example.healthcare.data.room.entities.UserEntity

data class LoginResult(
    val data: UserEntity?,
    val errorMessage: String?
)



