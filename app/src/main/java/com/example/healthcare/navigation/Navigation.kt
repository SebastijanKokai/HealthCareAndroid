package com.example.healthcare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.healthcare.ui.login.LoginScreen
import com.example.healthcare.ui.patients.HomeScreen
import com.example.healthcare.ui.patients.PatientEditScreen
import com.example.healthcare.ui.register.RegisterScreen
import com.example.healthcare.ui.patients.PatientDetailScreen
import com.example.healthcare.ui.profile.ProfileScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Auth.route) {
        navigation(
            startDestination = Screen.Login.route,
            route = Screen.Auth.route
        ) {
            composable(route = Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(route = Screen.Register.route) {
                RegisterScreen(navController)
            }
        }
        navigation(
            startDestination = Screen.Home.route,
            route = Screen.Main.route
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(
                route = Screen.PatientDetail.route,
                arguments = listOf(navArgument("patient_id") { type = NavType.StringType })
            ) {
                val id = it.arguments?.getString("patient_id")
                id?.let {
                    PatientDetailScreen(patientId = id)
                }
            }
            composable(route = Screen.PatientEdit.route) {
                PatientEditScreen()
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}