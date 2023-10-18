package com.example.healthcare.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.healthcare.data.Gender
import com.example.healthcare.data.room.entities.UserEntity.Companion.DEFAULT_UUID
import java.util.UUID

@Entity(tableName = "patient")
data class PatientEntity(
    @PrimaryKey val id: UUID = UUID.fromString(DEFAULT_UUID),
    val firstName: String?,
    val lastName: String?,
    val photoUrl: String?,
    val illness: String?,
    val gender: Gender?,
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