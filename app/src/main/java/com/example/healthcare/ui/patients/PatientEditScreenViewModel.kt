package com.example.healthcare.ui.patients

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.healthcare.data.Gender
import com.example.healthcare.models.patient.PatientDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PatientEditScreenViewModel @Inject constructor() : ViewModel() {
    val formData = FormData { firstName, lastName, gender, illness ->

    }

    private val _patientEditState: MutableStateFlow<PatientUiState> =
        MutableStateFlow(PatientUiState.Initial)
    val patientEditState: StateFlow<PatientUiState> = _patientEditState.asStateFlow()
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

    fun toDto(): PatientDto = PatientDto(
        firstName = firstName.value,
        lastName = lastName.value,
        gender = gender.value,
        illness = illness.value
    )
}

//data class FormData(
//    var firstName: String = "",
//    var lastName: String = "",
//    var gender: Gender = Gender.OTHER,
//    var illness: String = "",
//) {
//    fun isValid(): Boolean =
//        firstName.isNotEmpty() && lastName.isNotEmpty() && gender.value.isNotEmpty() && illness.isNotEmpty()
//
//    fun toDto(): PatientDto = PatientDto(
//        firstName = firstName,
//        lastName = lastName,
//        gender = gender,
//        illness = illness
//    )
//}

sealed class PatientUiState {
    object Initial : PatientUiState()
    data class Edit(val patientData: FormData) : PatientUiState()
    data class Error(val message: String?) : PatientUiState()
    object Loading : PatientUiState()
}