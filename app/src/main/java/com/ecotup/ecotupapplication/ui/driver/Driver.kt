package com.ecotup.ecotupapplication.ui.driver

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.ui.component.BottomNavigationDriver
import com.ecotup.ecotupapplication.ui.driver.history.HistoryScreenDriver
import com.ecotup.ecotupapplication.ui.driver.home.HomeScreenDriver
import com.ecotup.ecotupapplication.ui.driver.income.IncomeScreen
import com.ecotup.ecotupapplication.ui.driver.setting.SettingScreenDriver
import com.ecotup.ecotupapplication.ui.navigation.Screen

@Composable
fun Driver(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // jika rute sekarang tidak sama dengan login maka bottom navbar muncul
//            if (currentRoute != Screen.LoginScreen.route) {
            BottomNavigationDriver(navController = navController)
//            }
        }, modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreenDriver.route,
            modifier = modifier.padding(innerPadding)
        )
        {
            composable(Screen.HomeScreenDriver.route)
            {
                HomeScreenDriver()
            }
            composable(Screen.IncomeScreenDriver.route)
            {
                IncomeScreen()
            }
            composable(Screen.HistoryScreenDriver.route)
            {
                HistoryScreenDriver()
            }
            composable(Screen.SettingScreenDriver.route)
            {
                SettingScreenDriver()
            }
        }
    }
}
