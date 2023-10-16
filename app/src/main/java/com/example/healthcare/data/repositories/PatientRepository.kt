package com.example.healthcare.data.repositories

import com.example.healthcare.data.datasource.DataSource
import com.example.healthcare.data.datasource.LocalDataSource
import com.example.healthcare.data.room.dao.PatientDao
import com.example.healthcare.data.room.entities.Patient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PatientRepository @Inject constructor(private val localDataSource: DataSource) :
    IPatientRepository {
    override suspend fun getAll(): Flow<List<Patient>> {
        // TODO Here map, filter results...
        return localDataSource.getPatients()
    }

    override fun getById(id: String): Flow<Patient> = flow {
        // TODO To implement
    }
}