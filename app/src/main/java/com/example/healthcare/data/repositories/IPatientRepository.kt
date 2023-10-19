package com.example.healthcare.data.repositories

import com.example.healthcare.data.room.entities.PatientEntity
import kotlinx.coroutines.flow.Flow

interface IPatientRepository {
    suspend fun getAllLocally(): Flow<List<PatientEntity>>
    suspend fun getByIdLocally(id: String): Flow<PatientEntity>
    suspend fun insertLocally(patient: PatientEntity)
    suspend fun deleteLocally(id: String)
}