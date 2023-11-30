package com.ecotup.ecotupapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.ui.general.login.LoginScreen
import com.ecotup.ecotupapplication.ui.general.options.OptionScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.OptionScreen.route)
    {
        composable(route = Screen.OptionScreen.route)
        {
            OptionScreen(navController = navController)
        }
        composable(route = Screen.LoginUserScreen.route)
        {
            LoginScreen(navController = navController)
        }
    }
}