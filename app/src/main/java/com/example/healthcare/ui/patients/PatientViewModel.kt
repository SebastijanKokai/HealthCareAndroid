package com.example.healthcare.ui.patients

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class PatientViewModel @Inject constructor(private val repository: IPatientRepository) :
    ViewModel() {

    private val _patientResponse: MutableStateFlow<PatientState> =
        MutableStateFlow(PatientState.Loading)
    val patientResponse: StateFlow<PatientState> = _patientResponse.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll()
                .catch { exception ->
                    Log.e("Exception", exception.message.toString())
                    _patientResponse.value = PatientState.Error(exception.message)
                }
                .collect { patients ->
                    _patientResponse.value =
                        PatientState.Success(patients)
                }
        }
    }
}

sealed class PatientState {
    data class Success(val listOfPatients: List<PatientEntity>) : PatientState()
    data class Error(val error: String?) : PatientState()
    object Loading : PatientState()
}