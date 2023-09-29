package com.example.healthcare.data

import com.example.healthcare.data.room.entities.Patient
import java.time.LocalDate


fun fetchPatientRecords(): List<Patient> {
    val numberOfPatients = 10
    val url = "https://randomuser.me/api/?results=$numberOfPatients"
    val patientList: ArrayList<Patient> = ArrayList()

    for (i in 1..10) {
        patientList.add(
            Patient(
                id = "$i",
                firstName = "Name$i",
                lastName = "LastName$i",
                photoUrl = "https://randomuser.me/api/portraits/med/women/57.jpg",
                illness = "Illness$i",
                gender = null,
                address = null,
                dateOfBirth = LocalDate.now().minusYears(i.toLong()),
                lastVisit = null,
                sleepingPattern = null,
                physicallyActive = false,
                smokingStatus = false,
                diagnosis = null,
                prescribedMedicationList = null
            )
        )
    }

    return patientList
}

fun fetchPatientById(): Patient {
    return Patient(
        id = "1",
        firstName = "Name1",
        lastName = "LastName1",
        photoUrl = "https://randomuser.me/api/portraits/med/women/57.jpg",
        illness = "Illness1",
        gender = null,
        address = null,
        dateOfBirth = LocalDate.now().minusYears(1),
        lastVisit = null,
        sleepingPattern = null,
        physicallyActive = false,
        smokingStatus = false,
        diagnosis = null,
        prescribedMedicationList = null
    )
}