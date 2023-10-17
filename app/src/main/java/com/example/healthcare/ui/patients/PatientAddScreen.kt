package com.example.healthcare.ui.patients

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.models.dto.Gender
import com.example.healthcare.models.dto.PatientDto
import com.example.healthcare.ui.common.InputField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientAddScreen() {
    var patientAddRequest by remember { mutableStateOf(PatientDto()) }
    var isGenderMenuExpanded by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = patientAddRequest.firstName,
            onChange = { data -> patientAddRequest = patientAddRequest.copy(firstName = data) },
            label = "First name",
            placeholder = "Enter first name",
            leadingIcon = null
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = patientAddRequest.lastName,
            onChange = { data -> patientAddRequest = patientAddRequest.copy(lastName = data) },
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
                value = patientAddRequest.gender.value,
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
                            patientAddRequest = patientAddRequest.copy(gender = label)
                        })
                }
            }
        }
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = patientAddRequest.illness,
            onChange = { data -> patientAddRequest = patientAddRequest.copy(illness = data) },
            label = "Illness",
            placeholder = "Enter illness",
            leadingIcon = null
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (patientAddRequest.isValid()) {

                }
            },
        ) {
            Text(
                text = "Add patient"
            )
        }
    }
}

@Composable
@Preview
fun PatientAddScreenPreview() {
    PatientAddScreen()
}