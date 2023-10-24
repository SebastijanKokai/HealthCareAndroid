package com.example.healthcare.data.room.entities

import java.time.LocalDate

data class UserEntity(
    val userId: String,
    var username: String? = "",
    var firstName: String? = "",
    var lastName: String? = "",
    var contactNumber: String? = "",
    var profilePictureUrl: String? = "",
    var dateOfBirth: LocalDate? = LocalDate.now(),
    var email: String? = "",
) {
    companion object {
        const val DEFAULT_UUID = "00000000-0000-0000-0000-000000000000"
    }
}
