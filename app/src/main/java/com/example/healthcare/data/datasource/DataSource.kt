package com.example.healthcare.data.datasource

import com.example.healthcare.data.room.entities.Patient
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getPatients(): Flow<List<Patient>>
    fun getPatientById(id: String): Flow<Patient>
}