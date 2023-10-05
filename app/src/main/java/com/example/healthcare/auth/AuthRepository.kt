package com.example.healthcare.auth

import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    val oneTapClient: SignInClient,
    val signInRequest: BeginSignInRequest,
    val signUpRequest: BeginSignInRequest,
    val firebaseAuth: FirebaseAuth
) : IAuthRepository {

    override suspend fun signInWithGoogle(): BeginSignInResult? {
        return try {
            oneTapClient.beginSignIn(signInRequest).await()
        } catch (e: Exception) {
            null
        }
    }

    override fun test() {
        Log.e("123", "123")
    }

}