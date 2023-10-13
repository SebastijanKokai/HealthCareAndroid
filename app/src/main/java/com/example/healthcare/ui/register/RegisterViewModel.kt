package com.example.healthcare.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.auth.IAuthRepository
import com.example.healthcare.models.register.RegisterRequest
import com.example.healthcare.models.register.RegisterResult
import com.example.healthcare.models.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: IAuthRepository) :
    ViewModel() {

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

    fun resetState() {
        _state.update { RegisterState() }
    }
}