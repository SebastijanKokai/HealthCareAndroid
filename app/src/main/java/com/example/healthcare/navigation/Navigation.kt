package com.example.healthcare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.healthcare.ui.login.LoginScreen
import com.example.healthcare.ui.PatientsScreen
import com.example.healthcare.ui.patients.PatientEditScreen
import com.example.healthcare.ui.register.RegisterScreen
import com.example.healthcare.ui.patients.PatientDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = Screen.PatientsScreen.route) {
            PatientsScreen(navController)
        }
        composable(
            route = Screen.PatientDetailScreen.route,
            arguments = listOf(navArgument("patient_id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("patient_id")
            id?.let {
                PatientDetailScreen(patientId = id)
            }
        }
        composable(route = Screen.PatientEditScreen.route) {
            PatientEditScreen()
        }
    }
}