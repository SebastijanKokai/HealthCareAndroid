package com.example.healthcare.ui.patients

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.auth.IAuthRepository
import com.example.healthcare.data.repositories.IPatientRepository
import com.example.healthcare.data.room.entities.PatientEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  HomeScreenViewModel @Inject constructor(
    private val patientRepository: IPatientRepository, private val authRepository: IAuthRepository
) : ViewModel() {

    private val _patientResponse: MutableStateFlow<PatientState> =
        MutableStateFlow(PatientState.Loading)
    val patientResponse: StateFlow<PatientState> = _patientResponse.asStateFlow()

    private val _isLoggedIn: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    init {
        getAllLocally()
    }

    private fun getAllLocally() {
        viewModelScope.launch {
            patientRepository.getAllLocally().catch { exception ->
                Log.e("Exception", exception.message.toString())
                _patientResponse.value = PatientState.Error(exception.message)
            }.collect { patients ->
                _patientResponse.value = PatientState.Success(patients)
            }
        }
    }

    fun deletePatient(id: String) {
        viewModelScope.launch {
            runCatching {
                patientRepository.deleteLocally(id)
            }.onSuccess {
                getAllLocally()
            }.onFailure {
                _patientResponse.value = PatientState.Error(it.message)
            }
        }
    }

    fun addAuthChangeListener() {
        viewModelScope.launch {
            authRepository.getFirebaseAuthState()
                .catch { exception ->
                    Log.e("Exception", exception.message.toString())
                }.collect {
                    _isLoggedIn.value = it
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }


}

sealed class PatientState {
    data class Success(val listOfPatients: List<PatientEntity>) : PatientState()
    data class Error(val error: String?) : PatientState()
    object Loading : PatientState()
}