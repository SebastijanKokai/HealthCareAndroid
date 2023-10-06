package com.example.healthcare.auth

import android.content.Intent
import android.content.IntentSender
import com.example.healthcare.models.UserData
import com.example.healthcare.models.login.LoginResult

interface IAuthRepository {
    suspend fun signInWithGoogle(): IntentSender?
    suspend fun signInWithIntent(intent: Intent): LoginResult
    suspend fun signOut()
    fun getSignedInUser(): UserData?
}