package com.ecotup.ecotupapplication.ui.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.ui.component.BottomNavigationUser
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.EcotupApplicationTheme
import com.ecotup.ecotupapplication.ui.user.history.HistoryScreenUser
import com.ecotup.ecotupapplication.ui.user.home.HomeScreenUser
import com.ecotup.ecotupapplication.ui.user.scan.ScanningScreenUser
import com.ecotup.ecotupapplication.ui.user.setting.SettingScreenUser
import com.ecotup.ecotupapplication.ui.user.subscription.SubscriptionScreenUser

@Composable
fun User(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // jika rute sekarang tidak sama dengan login maka bottom navbar muncul
//            if (currentRoute != Screen.LoginScreen.route) {
                BottomNavigationUser(navController = navController)
//            }
        }, modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreenUser.route,
            modifier = modifier.padding(innerPadding)
        )
        {
            composable(Screen.HomeScreenUser.route)
            {
                HomeScreenUser()
            }
            composable(Screen.SubscriptionScreenUser.route)
            {
                SubscriptionScreenUser()
            }
            composable(Screen.ScanningScreenUser.route)
            {
                ScanningScreenUser()
            }
            composable(Screen.HistoryScreenUser.route)
            {
                HistoryScreenUser()
            }
            composable(Screen.SettingScreenUser.route)
            {
                SettingScreenUser()
            }
        }
    }
}
