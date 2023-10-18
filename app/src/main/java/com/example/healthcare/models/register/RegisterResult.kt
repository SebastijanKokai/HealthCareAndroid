package com.example.healthcare.models.register

import com.example.healthcare.data.room.entities.UserEntity

data class RegisterResult(
    val data: UserEntity?,
    val errorMessage: String?
)