package com.example.healthcare.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.healthcare.data.room.entities.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("SELECT * FROM patient")
    fun getAll(): Flow<List<Patient>>

    @Query("SELECT * FROM patient WHERE id = :patientId")
    fun getById(patientId: String): Flow<Patient>
}