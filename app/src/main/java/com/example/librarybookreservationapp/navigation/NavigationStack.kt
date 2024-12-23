package com.example.librarybookreservationapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.librarybookreservationapp.screens.HomeScreen
import com.example.librarybookreservationapp.screens.CartScreen
import com.example.librarybookreservationapp.screens.SettingsScreen

@Composable
fun NavigationStack(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
        composable(route = Screens.Profile.route) {
            CartScreen()
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen()
        }
    }

}
