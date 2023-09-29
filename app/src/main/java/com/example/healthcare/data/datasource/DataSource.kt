package com.example.healthcare.data.datasource

import com.example.healthcare.data.room.entities.Patient

interface DataSource {
    fun getPatients(): List<Patient>
    fun getPatientById(id: String): Patient
}