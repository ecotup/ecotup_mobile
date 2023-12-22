package com.ecotup.ecotupapplication.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.driver.Driver
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverScreen
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverScreenPassword
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverScreenVehicle
import com.ecotup.ecotupapplication.ui.general.authpage.AuthScreen
import com.ecotup.ecotupapplication.ui.general.login.LoginScreen
import com.ecotup.ecotupapplication.ui.general.onboarding.OnboardingScreen
import com.ecotup.ecotupapplication.ui.general.options.OptionScreen
import com.ecotup.ecotupapplication.ui.user.User
import com.ecotup.ecotupapplication.ui.user.registerUser.RegisterUserScreen
import com.ecotup.ecotupapplication.ui.user.registerUser.RegisterUserScreenPassword

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: NavigationViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
    val loginAsState = remember { mutableStateOf("") }
    val lifecycleOwner: LifecycleOwner = LocalContext.current as LifecycleOwner

    LaunchedEffect(key1 = Unit) {
        viewModel.getSessionToken().observe(lifecycleOwner) {
            loginAsState.value = when (it.role) {
                "user" -> "user"
                "driver" -> "driver"
                else -> ""
            }
        }

        navController.navigate(
            when (loginAsState.value) {
                "user" -> {
                    Screen.UserScreen.route
                }

                "driver" -> {
                    Screen.DriverScreen.route
                }

                else -> Screen.OnboardingScreen.route
            }
        )
    }

    NavHost(
        navController = navController, startDestination = Screen.OnboardingScreen.route
    ) {
        // User
        composable(route = Screen.UserScreen.route) {
            User()
        }

        // Driver
        composable(route = Screen.DriverScreen.route) {
            Driver()
        }

        // Option
        composable(route = Screen.OptionScreen.route) {
            OptionScreen(navController = navController)
        }

        // Auth
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(navController = navController)
        }

        // Login
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.OnboardingScreen.route) {
            OnboardingScreen(navController = navController)
        }

        // Register User Screen
        composable(route = Screen.RegisterUserScreen.route) {
            RegisterUserScreen(navController = navController)
        }

        // Register User Password
        composable(route = Screen.RegisterUserScreenPassword.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val email = backStackEntry.arguments?.getString("email")
            val phone = backStackEntry.arguments?.getString("phone")
            val lat = backStackEntry.arguments?.getString("lat")
            val long = backStackEntry.arguments?.getString("long")


            if (lat != null && long != null) {
                RegisterUserScreenPassword(
                    navController = navController,
                    name = name.toString(),
                    email = email.toString(),
                    phone = phone.toString(),
                    lat = lat.toDouble(),
                    long = long.toDouble()
                )
            }
        }

        // Register Driver Screen
        composable(route = Screen.RegisterDriverScreen.route) {
            RegisterDriverScreen(navController = navController)
        }

        // Register Driver Vehicle
        composable(route = Screen.RegisterDriverScreenVehicle.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val email = backStackEntry.arguments?.getString("email")
            val phone = backStackEntry.arguments?.getString("phone")
            val lat = backStackEntry.arguments?.getString("lat")
            val long = backStackEntry.arguments?.getString("long")

            if (lat != null && long != null) {
                RegisterDriverScreenVehicle(
                    navController = navController,
                    name = name.toString(),
                    email = email.toString(),
                    phone = phone.toString(),
                    lat = lat.toDouble(),
                    long = long.toDouble()
                )
            }

        }

        // Register Driver Password
        composable(route = Screen.RegisterDriverScreenPassword.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val email = backStackEntry.arguments?.getString("email")
            val phone = backStackEntry.arguments?.getString("phone")
            val lat = backStackEntry.arguments?.getString("lat")
            val long = backStackEntry.arguments?.getString("long")
            val type = backStackEntry.arguments?.getString("type")
            val license = backStackEntry.arguments?.getString("license")

            if (lat != null && long != null) {
                RegisterDriverScreenPassword(
                    navController = navController,
                    name = name.toString(),
                    email = email.toString(),
                    phone = phone.toString(),
                    lat = lat.toDouble(),
                    long = long.toDouble(),
                    type = type.toString(),
                    license = license.toString()
                )
            }

        }
    }
}