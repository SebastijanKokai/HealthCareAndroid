package com.example.healthcare.models.register

import com.example.healthcare.extension.isEmailValid
import com.example.healthcare.extension.isPasswordValid
import java.time.LocalDate

data class RegisterRequest(
//    var firstName: String = "",
//    var lastName: String = "",
//    var contactNumber: String = "",
//    var dateOfBirth: LocalDate = LocalDate.now(),
    var email: String = "",
    var password: String = "",
) {
    fun isValid() : Boolean {
        return email.isEmailValid() && password.isPasswordValid()
    }
}
