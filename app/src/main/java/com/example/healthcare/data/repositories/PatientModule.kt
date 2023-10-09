package com.example.healthcare.data.repositories

import com.example.healthcare.data.room.dao.PatientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PatientModule {

    @Provides
    @Singleton
    fun providePatientRepository(patientDao: PatientDao): IPatientRepository {
        return PatientRepository(
            patientDao
        )
    }
}