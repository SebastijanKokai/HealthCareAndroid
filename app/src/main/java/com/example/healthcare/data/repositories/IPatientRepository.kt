package com.example.healthcare.data.repositories

import com.example.healthcare.data.room.entities.PatientEntity
import kotlinx.coroutines.flow.Flow

interface IPatientRepository {
    suspend fun getAll(): Flow<List<PatientEntity>>
    fun getById(id: String): Flow<PatientEntity>
}