package com.example.healthcare.data.datasource

import com.example.healthcare.data.room.dao.PatientDao
import com.example.healthcare.data.room.entities.PatientEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val patientDao: PatientDao) : DataSource {

    override suspend fun getPatients(): Flow<List<PatientEntity>> = flow {
        val patients = patientDao.getAll()
        // Fake loading
        delay(1500)
        emit(patients)
    }.flowOn(Dispatchers.IO)


    override suspend fun getPatientById(id: String): Flow<PatientEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertPatient(patient: PatientEntity) {
        withContext(Dispatchers.IO) {
            delay(1500)
            patientDao.insert(patient)
        }
    }

    override suspend fun deletePatient(id: String) {
        withContext(Dispatchers.IO) {
            patientDao.delete(id)
        }
    }
}