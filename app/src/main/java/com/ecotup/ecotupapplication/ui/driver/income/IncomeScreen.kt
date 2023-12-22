package com.ecotup.ecotupapplication.ui.driver.income

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.component.SectionIncomeDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionPointDashboardDriver
import com.ecotup.ecotupapplication.ui.driver.home.HomeDriverViewModel
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun IncomeScreen(
    viewModel: HomeDriverViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ), modifier: Modifier = Modifier, navController: NavController
) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.background_doodle),
            contentDescription = "background_doodle",
            modifier = modifier.fillMaxSize()
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(GreenLight, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        ) {

        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            SpacerCustom(space = 5)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.time_icon_white),
                    contentDescription = "subs_logo_top",
                    modifier = modifier.size(30.dp)
                )

                SpacerCustom(space = 5)

                Text(
                    text = stringResource(R.string.income),
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            SpacerCustom(space = 15)

            SectionPointDashboardDriver(
                viewModel = viewModel,
                modifier = modifier,
                lifecycleOwner = context as LifecycleOwner
            )
            SpacerCustom(space = 10)
            LazyColumn(modifier = modifier.fillMaxSize())
            {
                items(1) { index ->
                    SectionIncomeDashboardDriver()
                    SpacerCustom(space = 5)
                }
            }
        }
    }


}