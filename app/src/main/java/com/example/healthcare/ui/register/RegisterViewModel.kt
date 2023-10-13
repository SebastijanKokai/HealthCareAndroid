package com.example.healthcare.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.auth.IAuthRepository
import com.example.healthcare.models.login.RegisterRequest
import com.example.healthcare.models.login.RegisterResult
import com.example.healthcare.models.login.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val authRepository: IAuthRepository) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val registerResult = authRepository.registerUser(
                registerRequest
            )
            onRegisterResult(registerResult)
        }
    }

    private fun onRegisterResult(registerResult: RegisterResult) {
        _state.update {
            it.copy(
                isRegisterSuccessful = registerResult.data != null,
                registerError = registerResult.errorMessage
            )
        }
    }
}