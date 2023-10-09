package com.example.healthcare.data.room

import android.content.Context
import androidx.room.Room
import com.example.healthcare.data.room.dao.PatientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "appDatabase"
        ).build()
    }

    @Provides
    fun providePatientDao(appDatabase: AppDatabase): PatientDao {
        return appDatabase.patientDao()
    }
}