package com.example.healthcare.modules

import com.example.healthcare.data.datasource.DataSource
import com.example.healthcare.data.datasource.LocalDataSource
import com.example.healthcare.data.repositories.IPatientRepository
import com.example.healthcare.data.repositories.PatientRepository
import com.example.healthcare.data.room.dao.PatientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
    fun provideLocalDataSource(
        patientDao: PatientDao,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): DataSource {
        return LocalDataSource(
            patientDao, coroutineDispatcher
        )
    }
}