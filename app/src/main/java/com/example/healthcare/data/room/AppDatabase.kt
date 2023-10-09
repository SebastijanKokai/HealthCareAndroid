package com.example.healthcare.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthcare.data.room.dao.PatientDao
import com.example.healthcare.data.room.entities.Patient

@Database(entities = [Patient::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao

//    companion object {
//        private const val DATABASE_NAME = "app_database.db"
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        DATABASE_NAME
//                    ).build()
//
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
}