package com.example.healthcare.data.repositories

import com.example.healthcare.data.room.dao.PatientDao
import com.example.healthcare.data.room.entities.Patient
import kotlinx.coroutines.flow.Flow

class PatientRepository constructor(private val patientDao: PatientDao) : IPatientRepository {
    override fun getAll(): Flow<List<Patient>> {
        return patientDao.getAll()
    }

    override fun getById(id: String): Flow<Patient> {
        return patientDao.getById(id)
    }
}