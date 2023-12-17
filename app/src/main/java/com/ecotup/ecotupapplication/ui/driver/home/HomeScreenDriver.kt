package com.ecotup.ecotupapplication.ui.driver.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.component.SectionAddressDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionJobDriver
import com.ecotup.ecotupapplication.ui.component.SectionPointDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionProfileDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionStatusDriver
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun HomeScreenDriver(
    viewModel: HomeDriverViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ), modifier: Modifier = Modifier, navController: NavController
) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(225.dp)
                .background(GreenLight, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        ) {

        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SpacerCustom(space = 10)

            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ecotup_logo_white_large),
                    contentDescription = "logo_white",
                    modifier = modifier.width(150.dp)

                )
                Row(modifier = modifier.align(Alignment.CenterEnd)) {
                    Image(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "notification",
                        modifier = modifier.size(20.dp).clickable {
                            navController.navigate(Screen.ScanningScreenUser.route)
                        }
                    )

                    SpacerCustom(space = 5)
                }
            }


            SpacerCustom(space = 15)

            SectionProfileDashboardDriver(
                viewModel = viewModel, lifecycleOwner = context as LifecycleOwner
            )

            SpacerCustom(space = 5)

            SectionAddressDashboardDriver(
                viewModel = viewModel, lifecycleOwner = context as LifecycleOwner
            )

            SpacerCustom(space = 6)

            LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
                item {
                    SectionPointDashboardDriver(
                        viewModel = viewModel, lifecycleOwner = context as LifecycleOwner
                    )

                    SpacerCustom(space = 10)
                    SectionStatusDriver()
                    SpacerCustom(space = 10)

                    SectionJobDriver()
                }
            }

        }
    }
}