package com.example.healthcare.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.healthcare.ui.login.LoginScreen
import com.example.healthcare.ui.patients.HomeScreen
import com.example.healthcare.ui.patients.PatientDetailScreen
import com.example.healthcare.ui.patients.PatientEditScreen
import com.example.healthcare.ui.profile.ProfileScreen
import com.example.healthcare.ui.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }
    }
}

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(
        startDestination = Screen.Home.route,
        route = MAIN_GRAPH_ROUTE
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