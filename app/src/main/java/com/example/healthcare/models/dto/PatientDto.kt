package com.example.healthcare.models.dto


data class PatientDto(
    var firstName: String = "",
    var lastName: String = "",
    var gender: Gender = Gender.OTHER,
    var address: String? = "",
    var illness: String = "",
    var photoUrl: String? = "",
//    var dateOfBirth: LocalDate?,
//    var lastVisit: LocalDate?,
    var sleepingPattern: String? = "",
    var physicallyActive: Boolean? = false,
    var smokingStatus: Boolean? = false,
    var diagnosis: String? = "",
) {
    fun isValid(): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && gender.value.isNotEmpty() && illness.isNotEmpty()
    }
}

enum class Gender(val value: String) {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
}