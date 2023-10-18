package com.example.healthcare.data.repositories

import com.example.healthcare.data.datasource.DataSource
import com.example.healthcare.data.room.entities.PatientEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PatientRepository @Inject constructor(private val localDataSource: DataSource) :
    IPatientRepository {
    override suspend fun getAll(): Flow<List<PatientEntity>> {
        // TODO Here map, filter results...
        return localDataSource.getPatients()
    }

    override fun getById(id: String): Flow<PatientEntity> = flow {
        // TODO To implement
    }
}