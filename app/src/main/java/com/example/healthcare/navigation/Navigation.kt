package com.example.healthcare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.ui.LoginScreen
import com.example.healthcare.ui.MainScreen
import com.example.healthcare.ui.RegisterScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val isLoggedIn = false // TODO implement isLoggedIn functionality
    val initialRoute = if (isLoggedIn) Screen.MainScreen.route else Screen.LoginScreen.route
    NavHost(navController = navController, startDestination = initialRoute) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen()
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen()
        }
    }
}