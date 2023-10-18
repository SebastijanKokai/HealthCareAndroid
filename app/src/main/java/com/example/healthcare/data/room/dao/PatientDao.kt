package com.example.healthcare.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.healthcare.data.room.entities.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("SELECT * FROM patient")
    fun getAll(): List<PatientEntity>

    @Query("SELECT * FROM patient WHERE id = :patientId")
    fun getById(patientId: String): Flow<PatientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: PatientEntity)
}