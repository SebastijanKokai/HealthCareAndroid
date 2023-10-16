package com.example.healthcare.data.repositories

import com.example.healthcare.data.datasource.DataSource
import com.example.healthcare.data.datasource.LocalDataSource
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
    fun providePatientRepository(localDataSource: DataSource): IPatientRepository {
        return PatientRepository(
            localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(patientDao: PatientDao): DataSource {
        return LocalDataSource(
            patientDao
        )
    }
}