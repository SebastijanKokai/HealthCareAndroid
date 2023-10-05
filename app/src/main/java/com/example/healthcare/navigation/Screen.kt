package com.example.healthcare.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object PatientDetailScreen : Screen("patient_detail_screen/{patient_id}")
}
