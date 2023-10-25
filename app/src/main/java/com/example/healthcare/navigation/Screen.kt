package com.example.healthcare.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Main : Screen("main")
    object Home : Screen("home_screen")
    object PatientDetail : Screen("patient_detail_screen/{patient_id}")
    object PatientEdit : Screen("patient_edit_screen")
    object Profile : Screen("profile_screen")
}
