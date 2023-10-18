package com.example.healthcare.models.patient

import com.example.healthcare.data.Gender
import com.example.healthcare.data.room.entities.PatientEntity

data class PatientDto(
    var firstName: String? = "",
    var lastName: String? = "",
    var gender: Gender? = Gender.UNKNOWN,
    var address: String? = "",
    var illness: String? = "",
    var photoUrl: String? = "",
    var sleepingPattern: String? = "",
    var physicallyActive: Boolean? = false,
    var smokingStatus: Boolean? = false,
    var diagnosis: String? = "",
)

fun PatientDto.toEntity() = PatientEntity(
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    address = address,
    illness = illness,
    photoUrl = photoUrl,
    sleepingPattern = sleepingPattern,
    physicallyActive = physicallyActive,
    smokingStatus = smokingStatus,
    diagnosis = diagnosis
)