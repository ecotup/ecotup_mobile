package com.ecotup.ecotupapplication.ui.component

import android.Manifest
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.IntentToFindDriver
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.checkForPermission

@Composable
fun SectionMainMenuDashboardUser(
    modifier: Modifier = Modifier,
    navigateToMaps: () -> Unit,
    context: Context,
    navController: NavController, viewModel: HomeUserViewModel
) {
    var subscription by remember { mutableStateOf("") }
    viewModel.getSessionUser().observe(context as LifecycleOwner)
    {
        subscription = it.subscription_value
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        var hasLocationPermission by remember {
            mutableStateOf(checkForPermission(context))
        }

        val locationPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            var isGranted = true
            permissions.entries.forEach {
                if (!it.value) {
                    isGranted = false
                    return@forEach
                }
            }

            if (isGranted) {
                hasLocationPermission = true
            }
        }

        MainMenu(
            modifier = modifier,
            image = painterResource(id = R.drawable.one_time),
            text = stringResource(id = R.string.one_time),
            contentDesc = stringResource(id = R.string.one_time),
            navigateToMaps = {
                if (hasLocationPermission) {

                } else {
                    hasLocationPermission = false
                }

                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                    )
                )

                IntentToFindDriver(context)
            }, context = context
        )

        MainMenu(
            modifier = modifier,
            image = painterResource(id = R.drawable.weekly_subscription),
            text = stringResource(R.string.weekly),
            contentDesc = stringResource(R.string.weekly),
            navigateToMaps = {
                navController.navigate(
                    route = Screen.SubscriptionWeeklyErrorScreen.route.replace(
                        "{subscription}", if (subscription == "7") "Weekly" else "Error"
                    )
                )
            },
            context = context
        )

        MainMenu(
            modifier = modifier,
            image = painterResource(id = R.drawable.monthly_subscription),
            text = stringResource(R.string.monthly),
            contentDesc = stringResource(R.string.monthly),
            navigateToMaps = {
                navController.navigate(
                    route = Screen.SubscriptionMonthlyErrorScreen.route.replace(
                        "{subscription}",
                        if (subscription.toString() == "30") "Monthly" else "Error"
                    )
                )
            },
            context = context
        )
    }
}

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    image: Painter,
    text: String,
    contentDesc: String,
    navigateToMaps: () -> Unit,
    context: Context
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = image,
            contentDescription = contentDesc,
            modifier = modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp)
                )
                .clickable {
                    navigateToMaps()
                }

        )
        SpacerCustom(space = 3)
        Text(
            text = text, style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.003.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}