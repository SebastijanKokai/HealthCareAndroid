package com.example.healthcare.data.datasource

import com.example.healthcare.data.room.entities.PatientEntity
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun getPatients(): Flow<List<PatientEntity>>
    suspend fun getPatientById(id: String): Flow<PatientEntity>
    suspend fun insertPatient(patient: PatientEntity)
}