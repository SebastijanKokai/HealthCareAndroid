package com.example.healthcare.models

import java.time.LocalDate

data class RegisterRequest(
    var firstName: String = "",
    var lastName: String = "",
    var contactNumber: String = "",
    var dateOfBirth: LocalDate = LocalDate.now(),
    var email: String = "",
    var username: String = "",
    var password: String = "",
)
