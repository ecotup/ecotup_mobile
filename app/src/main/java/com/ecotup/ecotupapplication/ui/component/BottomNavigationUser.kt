package com.ecotup.ecotupapplication.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.NavItemUser
import com.ecotup.ecotupapplication.ui.navigation.Screen

@Composable
fun BottomNavigationUser(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination?.route

        val navItem = listOf(
            NavItemUser(
                title = "Home",
                icon = painterResource(id = R.drawable.home),
                screen = Screen.HomeScreenUser,
                contentDescription = "home_user"
            ),
            NavItemUser(
                title = "Subscription",
                icon = painterResource(id = R.drawable.subscription),
                screen = Screen.SubscriptionScreenUser,
                contentDescription = "subscription_user"
            ),
            NavItemUser(
                title = "Scan",
                icon = painterResource(id = R.drawable.scanning),
                screen = Screen.ScanningScreenUser,
                contentDescription = "scan_user"
            ),
            NavItemUser(
                title = "History",
                icon = painterResource(id = R.drawable.history),
                screen = Screen.HistoryScreenUser,
                contentDescription = "history_user"
            ),
            NavItemUser(
                title = "Setting",
                icon = painterResource(id = R.drawable.setting),
                screen = Screen.SettingScreenUser,
                contentDescription = "setting_user"
            )
        )

        navItem.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route)
                    {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp)
                    )
                }
            )
        }
    }
}