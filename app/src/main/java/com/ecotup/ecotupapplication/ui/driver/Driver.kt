package com.ecotup.ecotupapplication.ui.driver

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.ui.component.BottomNavigationDriver
import com.ecotup.ecotupapplication.ui.driver.editAddressDriver.EditAddressScreenDriver
import com.ecotup.ecotupapplication.ui.driver.editProfileDriver.EditProfileScreenDriver
import com.ecotup.ecotupapplication.ui.driver.history.DetailHistoryTransactionScreenDriver
import com.ecotup.ecotupapplication.ui.driver.history.HistoryScreenDriver
import com.ecotup.ecotupapplication.ui.driver.home.HomeScreenDriver
import com.ecotup.ecotupapplication.ui.driver.income.IncomeScreen
import com.ecotup.ecotupapplication.ui.driver.setting.SettingScreenDriver
import com.ecotup.ecotupapplication.ui.general.about.AboutScreen
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.user.scan.ScanningScreenUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Driver(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            // jika rute sekarang tidak sama dengan login maka bottom navbar muncul
            if (currentRoute != Screen.LoginScreen.route && currentRoute != Screen.EditProfileScreenDriver.route && currentRoute != Screen.EditAddressScreenDriver.route && currentRoute != Screen.AboutScreen.route && currentRoute != Screen.DetailHistoryTransactionScreenDriver.route) {
            BottomNavigationDriver(navController = navController)
            }
        }, modifier = modifier
    ) { innerPadding ->

        // Mengatur agar ketika back tidak kembali ke onboarding tetapi langsung leave
        BackHandler {
            if (currentRoute == Screen.HomeScreenUser.route) {
                GlobalScope.launch {
                    delay(200)
                    (context as? ComponentActivity)?.finish()
                }
            } else {
                navController.popBackStack()
            }
        }

        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreenDriver.route,
            modifier = modifier.padding(innerPadding)
        )
        {
            composable(Screen.HomeScreenDriver.route)
            {
                HomeScreenDriver(navController = navController)
            }
            composable(Screen.IncomeScreenDriver.route)
            {
                IncomeScreen(navController = navController)
            }
            composable(Screen.HistoryScreenDriver.route)
            {
                HistoryScreenDriver(navController = navController)
            }
            composable(Screen.SettingScreenDriver.route)
            {
                SettingScreenDriver(navController = navController)
            }
            composable(Screen.ScanningScreenUser.route)
            {
                ScanningScreenUser(navController = navController)
            }
            composable(route = Screen.EditProfileScreenDriver.route) {
                EditProfileScreenDriver(navController = navController)
            }
            composable(route = Screen.EditAddressScreenDriver.route) {
                EditAddressScreenDriver(navController = navController)
            }
            composable(route = Screen.AboutScreen.route) {
                AboutScreen(navController = navController)
            }
            composable(route = Screen.DetailHistoryTransactionScreenDriver.route) {
                DetailHistoryTransactionScreenDriver(navController = navController)
            }
        }
    }
}
