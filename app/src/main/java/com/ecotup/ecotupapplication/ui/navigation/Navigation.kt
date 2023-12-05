package com.ecotup.ecotupapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.ui.driver.Driver
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverScreen
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverScreenPassword
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverScreenVehicle
import com.ecotup.ecotupapplication.ui.general.login.LoginScreen
import com.ecotup.ecotupapplication.ui.general.onboarding.OnboardingScreen
import com.ecotup.ecotupapplication.ui.general.options.OptionScreen
import com.ecotup.ecotupapplication.ui.user.User
import com.ecotup.ecotupapplication.ui.user.registerUser.RegisterUserScreen
import com.ecotup.ecotupapplication.ui.user.registerUser.RegisterUserScreenPassword

@Composable
fun Navigation() {
    val navController = rememberNavController()
    // Dibagian start Destination letakkan If membaca pref login user
    val pref = "a"
    NavHost(navController = navController, startDestination = if(pref == "a") Screen.OnboardingScreen.route else Screen.LoginScreen.route)
    {
        // User
        composable(route = Screen.UserScreen.route)
        {
            User()
        }

        // Driver
        composable(route = Screen.DriverScreen.route)
        {
            Driver()
        }

        // Option
        composable(route = Screen.OptionScreen.route)
        {
            OptionScreen(navController = navController)
        }

        // Login
        composable(route = Screen.LoginScreen.route)
        {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.OnboardingScreen.route)
        {
            OnboardingScreen(navController = navController)
        }

        // Register User Screen
        composable(route = Screen.RegisterUserScreen.route)
        {
            RegisterUserScreen(navController = navController)
        }

        // Register User Password
        composable(route = Screen.RegisterUserScreenPassword.route)
        {
            RegisterUserScreenPassword(navController = navController)
        }

        // Register Driver Screen
        composable(route = Screen.RegisterDriverScreen.route)
        {
            RegisterDriverScreen(navController = navController)
        }

        // Register Driver Vehicle
        composable(route = Screen.RegisterDriverScreenVehicle.route)
        {
            RegisterDriverScreenVehicle(navController = navController)
        }

        // Register Driver Password
        composable(route = Screen.RegisterDriverScreenPassword.route)
        {
            RegisterDriverScreenPassword(navController = navController)
        }
    }
}