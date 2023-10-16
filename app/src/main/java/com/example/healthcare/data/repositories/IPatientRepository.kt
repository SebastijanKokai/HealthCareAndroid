package com.example.healthcare.data.repositories

import com.example.healthcare.data.room.entities.Patient
import kotlinx.coroutines.flow.Flow

interface IPatientRepository {
    suspend fun getAll(): Flow<List<Patient>>
    fun getById(id: String): Flow<Patient>
}