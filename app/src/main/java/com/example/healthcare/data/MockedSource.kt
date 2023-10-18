package com.example.healthcare.data

import com.example.healthcare.data.room.entities.PatientEntity


fun fetchPatientRecords(): List<PatientEntity> {
    val numberOfPatients = 10
    val url = "https://randomuser.me/api/?results=$numberOfPatients"
    val patientList: ArrayList<PatientEntity> = ArrayList()

    for (i in 1..10) {
        patientList.add(
            PatientEntity(
                firstName = "Name$i",
                lastName = "LastName$i",
                photoUrl = "https://randomuser.me/api/portraits/med/women/57.jpg",
                illness = "Illness$i",
                gender = Gender.OTHER,
                address = null,
//                dateOfBirth = LocalDate.now().minusYears(i.toLong()),
//                lastVisit = null,
                sleepingPattern = null,
                physicallyActive = false,
                smokingStatus = false,
                diagnosis = null,
            )
        )
    }

    return patientList
}

fun fetchPatientById(): PatientEntity {
    return PatientEntity(
        firstName = "Name1",
        lastName = "LastName1",
        photoUrl = "https://randomuser.me/api/portraits/med/women/57.jpg",
        illness = "Illness1",
        gender = Gender.OTHER,
        address = null,
//        dateOfBirth = LocalDate.now().minusYears(1),
//        lastVisit = null,
        sleepingPattern = null,
        physicallyActive = false,
        smokingStatus = false,
        diagnosis = null,
    )
}