package com.ecotup.ecotupapplication.ui.user.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.component.SectionSubscription
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SubscriptionMonthly(
    navController: NavController,
    modifier: Modifier = Modifier,
    subscription: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (subscription == "Monthly") {
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
                        .padding(16.dp)
                )
                {
                    SpacerCustom(space = 5)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.subscription),
                            contentDescription = "subscription",
                            modifier = modifier.size(30.dp)
                        )

                        SpacerCustom(space = 5)

                        Text(
                            text = stringResource(R.string.subscription),
                            modifier = modifier,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = GreenLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                letterSpacing = 0.003.sp
                            )
                        )
                    }

                    SpacerCustom(space = 15)

                    LazyColumn(modifier = modifier.fillMaxSize())
                    {
                        items(30) { index ->
                            SpacerCustom(space = 2)
                            SectionSubscription(subscription = "Monthly")
                            SpacerCustom(space = 2)
                        }
                    }
                }
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.no_subscription),
                contentDescription = "no_subscription",
                modifier = modifier.size(200.dp)
            )
        }
    }
}