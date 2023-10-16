package com.example.healthcare.auth

import android.content.Intent
import android.content.IntentSender
import com.example.healthcare.models.user.UserData
import com.example.healthcare.models.login.LoginRequest
import com.example.healthcare.models.login.LoginResult
import com.example.healthcare.models.register.RegisterRequest
import com.example.healthcare.models.register.RegisterResult

interface IAuthRepository {
    suspend fun registerUser(registerRequest: RegisterRequest) : RegisterResult
    suspend fun login(loginRequest: LoginRequest) : LoginResult
    suspend fun loginWithGoogle(): IntentSender?
    suspend fun loginWithIntent(intent: Intent): LoginResult
    suspend fun logout()
    fun getLoggedInUser(): UserData?
}