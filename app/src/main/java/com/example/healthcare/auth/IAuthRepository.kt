package com.example.healthcare.auth

import com.google.android.gms.auth.api.identity.BeginSignInResult

interface IAuthRepository {
    suspend fun signInWithGoogle(): BeginSignInResult?
    fun test()
}