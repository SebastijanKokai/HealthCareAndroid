package com.example.healthcare.ui.profile

import androidx.lifecycle.ViewModel
import com.example.healthcare.auth.IAuthRepository
import com.example.healthcare.data.room.entities.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authRepository: IAuthRepository) :
    ViewModel() {

    private val _userData: MutableStateFlow<UserEntity?> =
        MutableStateFlow(
            UserEntity(
                userId = UserEntity.DEFAULT_UUID,
            )
        )
    var userData: StateFlow<UserEntity?> = _userData.asStateFlow()

    init {
        _userData.value = authRepository.getLoggedInUser()
    }
}