package com.example.healthcare.datasource

import com.example.healthcare.models.PatientData
import java.time.LocalDate


fun fetchPatientRecords(): List<PatientData> {
    val numberOfPatients = 10
    val url = "https://randomuser.me/api/?results=$numberOfPatients"
    val patientList: ArrayList<PatientData> = ArrayList()

    for (i in 1..10) {
        patientList.add(
            PatientData(
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