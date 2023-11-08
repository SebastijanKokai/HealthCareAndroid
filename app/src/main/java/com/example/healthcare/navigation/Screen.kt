package com.example.healthcare.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val MAIN_GRAPH_ROUTE = "main"
const val DRAWER_GRAPH_ROUTE = "drawer"

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Main : Screen("drawer_screen")
    object Home : Screen("home_screen")
    object Profile : Screen("profile_screen")
    object PatientDetail : Screen("patient_detail_screen/{patient_id}")
    object PatientEdit : Screen("patient_edit_screen")
}
