package com.example.healthcare.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object PatientsScreen : Screen("patients_screen")
    object PatientDetailScreen : Screen("patient_detail_screen/{patient_id}")
    object PatientEditScreen : Screen("patient_edit_screen")
    object ProfileScreen : Screen("profile_screen")
}
