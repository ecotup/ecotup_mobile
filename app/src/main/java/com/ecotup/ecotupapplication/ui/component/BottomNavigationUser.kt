package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.NavItemUser
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun BottomNavigationUser(
    modifier: Modifier = Modifier, navController: NavHostController
) {
    NavigationBar(
        modifier = modifier
            .height(65.dp)
            .padding(0.dp)
    ) {
        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination?.route

        val navItem = listOf(
            NavItemUser(
                title = "Home",
                icon = painterResource(id = R.drawable.home),
                screen = Screen.HomeScreenUser,
                contentDescription = "home_user"
            ), NavItemUser(
                title = "Subscription",
                icon = painterResource(id = R.drawable.subscription),
                screen = Screen.SubscriptionScreenUser,
                contentDescription = "subscription_user"
            ), NavItemUser(
                title = "Scan",
                icon = painterResource(id = R.drawable.camera),
                screen = Screen.ScanningScreenUser,
                contentDescription = "scan_user"
            ), NavItemUser(
                title = "History",
                icon = painterResource(id = R.drawable.history),
                screen = Screen.HistoryScreenUser,
                contentDescription = "history_user"
            ), NavItemUser(
                title = "Setting",
                icon = painterResource(id = R.drawable.setting),
                screen = Screen.SettingScreenUser,
                contentDescription = "setting_user"
            )
        )

        navItem.map { item ->
            NavigationBarItem(selected = currentRoute == item.screen.route, onClick = {
                navController.navigate(item.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }, icon = {
                val iconMod = if (currentRoute == item.screen.route) {
                    GreenLight
                } else {
                    Color.Gray
                }

                Icon(
                    painter = item.icon,
                    contentDescription = item.title,
                    tint = iconMod,
                    modifier = modifier.size(18.dp)
                )

            }, label = {
                    val labelColor = if (currentRoute == item.screen.route) {
                        GreenLight
                    } else {
                        Color.Gray
                    }
                    val weight = if (currentRoute == item.screen.route) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Medium
                    }
                    Text(
                        text = item.title, style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 9.sp, color = labelColor, fontWeight = weight
                        )
                    )
                })
        }
    }
}
