package com.example.healthcare.data.repositories

import com.example.healthcare.data.room.entities.Patient

interface IPatientRepository {
    fun getAll(): List<Patient>
    fun getById(): Patient
}