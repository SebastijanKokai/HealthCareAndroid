package com.example.healthcare.ui.login

import android.app.Activity.RESULT_OK
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.auth.IAuthRepository
import com.example.healthcare.models.login.LoginRequest
import com.example.healthcare.models.login.LoginResult
import com.example.healthcare.models.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: IAuthRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun googleLogin(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
        viewModelScope.launch {
            val loginIntentSender = authRepository.loginWithGoogle()
            launcher.launch(
                IntentSenderRequest.Builder(
                    loginIntentSender ?: return@launch
                ).build()
            )
        }
    }

    fun handleGoogleLoginResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            viewModelScope.launch {
                val loginResult = authRepository.loginWithIntent(
                    intent = result.data ?: return@launch
                )
                onLoginResult(loginResult)
            }
        }
    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val loginResult = authRepository.login(loginRequest)
            onLoginResult(loginResult)
        }
    }

    private fun onLoginResult(result: LoginResult) {
        _state.update {
            it.copy(
                isLoginSuccessful = result.data != null,
                loginError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { LoginState() }
    }

    fun isLoggedIn(): Boolean {
        return authRepository.getLoggedInUser() != null
    }
}