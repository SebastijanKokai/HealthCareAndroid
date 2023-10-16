package com.example.healthcare.models.user

import java.time.LocalDate

data class UserData(
    val userId: String,
    var username: String?,
    var firstName: String? = "",
    var lastName: String? = "",
    var contactNumber: String?,
    var profilePictureUrl: String?,
    var dateOfBirth: LocalDate? = LocalDate.now(),
    var email: String? = "",
)
