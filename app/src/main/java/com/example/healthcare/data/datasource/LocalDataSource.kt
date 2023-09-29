package com.example.healthcare.data.datasource

import com.example.healthcare.data.room.entities.Patient
import com.example.healthcare.data.room.dao.PatientDao

class LocalDataSource constructor(private val patientDao: PatientDao) : DataSource {

    override fun getPatients(): List<Patient> {
        return patientDao.getAll()
    }

    override fun getPatientById(id: String): Patient {
        return patientDao.getById(id)
    }
}