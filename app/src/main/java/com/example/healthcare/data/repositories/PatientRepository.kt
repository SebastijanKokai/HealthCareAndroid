package com.example.healthcare.data.repositories

import com.example.healthcare.data.datasource.DataSource
import com.example.healthcare.data.room.entities.PatientEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PatientRepository @Inject constructor(private val localDataSource: DataSource) :
    IPatientRepository {
    override suspend fun getAllLocally(): Flow<List<PatientEntity>> {
        // TODO Here map, filter results...
        return localDataSource.getPatients()
    }

    override suspend fun getByIdLocally(id: String): Flow<PatientEntity> = flow {
        // TODO To implement
    }

    override suspend fun insertLocally(patient: PatientEntity) {
        localDataSource.insertPatient(patient)
    }

    override suspend fun deleteLocally(id: String) {
        localDataSource.deletePatient(id)
    }
}