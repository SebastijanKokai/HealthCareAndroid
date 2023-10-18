package com.example.healthcare.ui.patients

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthcare.data.Gender
import com.example.healthcare.ui.common.ButtonWithLoadingIndicator
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
        ExposedDropdownMenuBox(
            expanded = isGenderMenuExpanded,
            onExpandedChange = {
                isGenderMenuExpanded = !isGenderMenuExpanded
            }) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                value = viewModel.formData.gender.value?.value ?: "Unknown",
                onValueChange = { },
                label = { Text("Please specify gender") },
                trailingIcon = {
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
            )
            ExposedDropdownMenu(
                expanded = isGenderMenuExpanded,
                onDismissRequest = { isGenderMenuExpanded = false },
            ) {
                Gender.values().forEach { label ->
                    DropdownMenuItem(
                        text = { Text(text = label.value) },
                        onClick = {
                            isGenderMenuExpanded = false
                            viewModel.formData.onGenderChanged(label)
                        })
                }
            }
        }
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