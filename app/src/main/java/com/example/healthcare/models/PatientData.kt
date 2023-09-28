package com.example.healthcare.models

import java.time.LocalDate

data class PatientData(
    val id: String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String?,
    val illness: String?,
    val gender: String?,
    val address: String?,
    val dateOfBirth: LocalDate?,
    val lastVisit: LocalDate?,
    val sleepingPattern: String?,
    val physicallyActive: Boolean?,
    val smokingStatus: Boolean?,
    val diagnosis: String?,
    val prescribedMedicationList: List<Medication>?,
)

data class Medication(
    val name: String,
    val description: String
)