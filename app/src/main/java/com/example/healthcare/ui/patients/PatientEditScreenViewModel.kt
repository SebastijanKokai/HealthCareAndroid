package com.example.healthcare.ui.patients

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.data.Gender
import com.example.healthcare.data.repositories.IPatientRepository
import com.example.healthcare.models.patient.PatientDto
import com.example.healthcare.models.patient.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientEditScreenViewModel @Inject constructor(private val repository: IPatientRepository) :
    ViewModel() {
    val formData = FormData { firstName, lastName, gender, illness ->
    }

    private val _patientEditState: MutableStateFlow<PatientUiState> =
        MutableStateFlow(PatientUiState.Initial)
    val patientEditState: StateFlow<PatientUiState> = _patientEditState.asStateFlow()

    fun insertToDb() {
        _patientEditState.value = PatientUiState.Loading

        if (formData.isValid().not()) {
            _patientEditState.value = PatientUiState.Error("Fill in patient data.")
            return
        }

        viewModelScope.launch {
            formData.toDto().runCatching {
                toEntity()
            }.mapCatching {
                repository.insertLocally(it)
            }.onSuccess {
                _patientEditState.value = PatientUiState.Initial
            }.onFailure {
                Log.e(PatientEditScreenViewModel::class.java.name, it.message.toString())
                _patientEditState.value = PatientUiState.Error(it.message)
            }
        }
    }
}

data class FormData(val onInputChanged: (String?, String?, Gender?, String?) -> Unit) {
    private val mutableFirstName = mutableStateOf<String?>(null)
    val firstName: State<String?> = mutableFirstName

    private val mutableLastName = mutableStateOf<String?>(null)
    val lastName: State<String?> = mutableLastName

    private val mutableGender = mutableStateOf<Gender?>(null)
    val gender: State<Gender?> = mutableGender

    private val mutableIllness = mutableStateOf<String?>(null)
    val illness: State<String?> = mutableIllness

    val onFirstNameChanged: (String) -> Unit = {
        mutableFirstName.value = it
        onInputChanged(firstName.value, lastName.value, gender.value, illness.value)
    }

    val onLastNameChanged: (String) -> Unit = {
        mutableLastName.value = it
        onInputChanged(firstName.value, lastName.value, gender.value, illness.value)
    }

    val onGenderChanged: (Gender) -> Unit = {
        mutableGender.value = it
        onInputChanged(firstName.value, lastName.value, gender.value, illness.value)
    }

    val onIllnessChanged: (String) -> Unit = {
        mutableIllness.value = it
        onInputChanged(firstName.value, lastName.value, gender.value, illness.value)
    }

    fun isValid(): Boolean {
        return firstName.value?.isNotEmpty() ?: false
                && lastName.value?.isNotEmpty() ?: false
                && gender.value?.value?.isNotEmpty() ?: false
                && illness.value?.isNotEmpty() ?: false
    }

    fun resetState() {
        mutableFirstName.value = ""
        mutableLastName.value = ""
        mutableGender.value = Gender.UNKNOWN
        mutableIllness.value = ""
    }

    fun toDto(): PatientDto = PatientDto(
        firstName = firstName.value,
        lastName = lastName.value,
        gender = gender.value,
        illness = illness.value
    )
}

sealed class PatientUiState {
    object Initial : PatientUiState()
    data class Error(val message: String?) : PatientUiState()
    object Loading : PatientUiState()
}