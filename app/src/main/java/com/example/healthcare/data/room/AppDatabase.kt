package com.example.healthcare.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthcare.data.Converters
import com.example.healthcare.data.room.dao.PatientDao
import com.example.healthcare.data.room.entities.PatientEntity

@Database(entities = [PatientEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
}