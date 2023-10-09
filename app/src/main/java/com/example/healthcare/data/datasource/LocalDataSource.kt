package com.example.healthcare.data.datasource

import com.example.healthcare.data.room.dao.PatientDao
import com.example.healthcare.data.room.entities.Patient
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val patientDao: PatientDao) : DataSource {

    override fun getPatients(): Flow<List<Patient>> {
        return patientDao.getAll()
    }

    override fun getPatientById(id: String): Flow<Patient> {
        return patientDao.getById(id)
    }
}