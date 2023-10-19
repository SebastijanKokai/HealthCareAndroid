package com.example.healthcare.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
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
    fun insert(patient: PatientEntity)

    @Query("DELETE FROM patient WHERE id = :patientId")
    fun delete(patientId: String)
}