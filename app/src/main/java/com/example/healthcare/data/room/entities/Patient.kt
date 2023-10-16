package com.example.healthcare.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Patient(
    @PrimaryKey val id: String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String?,
    val illness: String?,
    val gender: String?,
    val address: String?,
//    val dateOfBirth: LocalDate?,
//    val lastVisit: LocalDate?,
    val sleepingPattern: String?,
    val physicallyActive: Boolean?,
    val smokingStatus: Boolean?,
    val diagnosis: String?,
)

data class Medication(
    val name: String,
    val description: String
)