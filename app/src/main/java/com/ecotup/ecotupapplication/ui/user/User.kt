package com.ecotup.ecotupapplication.ui.user

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
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
import com.ecotup.ecotupapplication.ui.component.BottomNavigationUser
import com.ecotup.ecotupapplication.ui.general.about.AboutScreen
import com.ecotup.ecotupapplication.ui.maps.MapsScreen
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.user.editAddressUser.EditAddressScreenUser
import com.ecotup.ecotupapplication.ui.user.editProfileUser.EditProfileScreenUser
import com.ecotup.ecotupapplication.ui.user.history.HistoryScreenUser
import com.ecotup.ecotupapplication.ui.user.home.HomeScreenUser
import com.ecotup.ecotupapplication.ui.user.reward.DetailRewardScreen
import com.ecotup.ecotupapplication.ui.user.reward.RewardScreen
import com.ecotup.ecotupapplication.ui.user.reward.SuccessGetRewardScreen
import com.ecotup.ecotupapplication.ui.user.scan.ScanningScreenUser
import com.ecotup.ecotupapplication.ui.user.setting.SettingScreenUser
import com.ecotup.ecotupapplication.ui.user.subscription.DetailSubscriptionScreen
import com.ecotup.ecotupapplication.ui.user.subscription.SubscriptionScreenUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun User(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            // jika rute sekarang tidak sama dengan login maka bottom navbar muncul
            if (currentRoute != Screen.MapsScreen.route && currentRoute != Screen.DetailSubscriptionScreen.route && currentRoute != Screen.ScanningScreenUser.route && currentRoute != Screen.ResultScanningScreenUser.route && currentRoute != Screen.RewardScreen.route && currentRoute != Screen.DetailRewardScreen.route && currentRoute != Screen.SuccessGetRewardScreen.route && currentRoute != Screen.EditProfileScreenUser.route && currentRoute != Screen.EditAddressScreenUser.route && currentRoute != Screen.AboutScreen.route){
                BottomNavigationUser(navController = navController)
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        // Mengatur agar ketika back tidak kembali ke onboarding tetapi langsung leave
        BackHandler {
            if (currentRoute == Screen.HomeScreenUser.route) {
                GlobalScope.launch {
                    delay(200)
                    (context as? androidx.activity.ComponentActivity)?.finish()
                }
            } else {
                navController.popBackStack()
            }
        }

        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreenUser.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreenUser.route) {
                HomeScreenUser(navigateToMaps = { navController.navigate(Screen.MapsScreen.route) }, navController = navController)
            }
            composable(Screen.SubscriptionScreenUser.route) {
                SubscriptionScreenUser(navController = navController)
            }
            composable(Screen.ScanningScreenUser.route) {
                ScanningScreenUser(navController = navController)
            }
            composable(Screen.HistoryScreenUser.route) {
                HistoryScreenUser()
            }
            composable(Screen.SettingScreenUser.route) {
                SettingScreenUser(navController = navController)
            }
            composable(route = Screen.MapsScreen.route) {
                MapsScreen(navController = navController, context = context)
            }
            composable(route = Screen.DetailSubscriptionScreen.route) { backStackEntry ->
                val image = backStackEntry.arguments?.getString("image")
                val title = backStackEntry.arguments?.getString("title")
                val pickup = backStackEntry.arguments?.getString("pickup")
                val price = backStackEntry.arguments?.getString("price")
                val description = backStackEntry.arguments?.getString("description")
                if (image != null && title != null && pickup != null && price != null && description != null) {
                    DetailSubscriptionScreen(
                        navController = navController,
                        context = context,
                        image = image,
                        title = title,
                        pickup = pickup,
                        price = price,
                        description = description
                    )
                }
            }
            composable(route = Screen.RewardScreen.route) {
               RewardScreen(navController = navController)
            }
            composable(route = Screen.DetailRewardScreen.route) {
               DetailRewardScreen(navController = navController)
            }
            composable(route = Screen.SuccessGetRewardScreen.route) {
               SuccessGetRewardScreen(navController = navController)
            }
            composable(route = Screen.EditProfileScreenUser.route) {
                EditProfileScreenUser(navController = navController)
            }
            composable(route = Screen.EditAddressScreenUser.route) {
                EditAddressScreenUser(navController = navController)
            }
            composable(route = Screen.AboutScreen.route) {
                AboutScreen(navController = navController)
            }

        }
    }
}