package com.example.healthcare.ui.login

import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import com.example.healthcare.auth.AuthRepository
import com.example.healthcare.auth.IAuthRepository
import com.google.android.gms.auth.api.identity.BeginSignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: IAuthRepository) : ViewModel() {

    suspend fun googleSignIn(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
    }

    fun test() {
        authRepository.test()
    }
    
}