package com.example.healthcare.ui.patients

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthcare.data.Gender
import com.example.healthcare.ui.common.ButtonWithLoadingIndicator
import com.example.healthcare.ui.common.DropdownMenu
import com.example.healthcare.ui.common.InputField

@Composable
fun PatientEditScreen(viewModel: PatientEditScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val patientUiState: PatientUiState by viewModel.patientEditState.collectAsState()
    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
    isLoading = false

    when (val state = patientUiState) {
        PatientUiState.Initial -> {
            viewModel.formData.resetState()
        }

        is PatientUiState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
        }

        PatientUiState.Loading -> {
            isLoading = true
        }
    }

    PatientEditContentScreen(viewModel, isLoading)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientEditContentScreen(viewModel: PatientEditScreenViewModel, isLoading: Boolean) {
    var isGenderMenuExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.formData.firstName.value.orEmpty(),
            onChange = { data -> viewModel.formData.onFirstNameChanged(data) },
            label = "First name",
            placeholder = "Enter first name",
            leadingIcon = null
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.formData.lastName.value.orEmpty(),
            onChange = { data -> viewModel.formData.onLastNameChanged(data) },
            label = "Last name",
            placeholder = "Enter last name",
            leadingIcon = null
        )
        DropdownMenu(
            items = Gender.values(),
            hintText = "Please specify gender",
            isExpanded = isGenderMenuExpanded,
            value = viewModel.formData.gender.value?.value ?: "Unknown",
            onValueChange = { },
            onExpandedChange = { isGenderMenuExpanded = !isGenderMenuExpanded },
            onDismissRequest = { isGenderMenuExpanded = false },
            onItemClick = {
                isGenderMenuExpanded = false
                viewModel.formData.onGenderChanged(it)
            }
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.formData.illness.value.orEmpty(),
            onChange = { data -> viewModel.formData.onIllnessChanged(data) },
            label = "Illness",
            placeholder = "Enter illness",
            leadingIcon = null
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            ButtonWithLoadingIndicator(
                isLoading = isLoading,
                text = "Add patient"
            ) {
                viewModel.insertToDb()
            }
        }
    }
}

@Composable
@Preview
fun PatientAddScreenPreview() {

}