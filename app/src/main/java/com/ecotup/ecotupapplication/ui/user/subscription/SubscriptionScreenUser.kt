package com.ecotup.ecotupapplication.ui.user.subscription

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SubscriptionScreenUser(modifier: Modifier = Modifier, navController: NavController) {
    Box(modifier = modifier.fillMaxSize()) {
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
                    painter = painterResource(id = R.drawable.subscription_logo_top),
                    contentDescription = "subs_logo_top",
                    modifier = modifier.size(30.dp)
                )

                SpacerCustom(space = 5)

                Text(
                    text = "Subscription",
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

            SubscriptionComponent(
                titleSubs = "Weekly Subscription",
                priceSubs = "RP. 50.000",
                pickUpSubs = 7,
                navController = navController,
                image = "weekly",
                desc = "Weekly Subscription is a subscription that is valid for 7 days. You can pick up your trash 7 times in 7 days. The price is Rp.50.000"
            )
            SpacerCustom(space = 8)
            SubscriptionComponent(
                titleSubs = "Monthly Subscription",
                priceSubs = "RP. 210.000",
                pickUpSubs = 30,
                navController = navController,
                image = "monthly",
                desc = "Monthly Subscription is a subscription that is valid for 30 days. You can pick up your trash 30 times in 30 days. The price is Rp.210.000"
            )
        }
    }
}


@Composable
fun SubscriptionComponent(
    modifier: Modifier = Modifier,
    titleSubs: String,
    priceSubs: String,
    pickUpSubs: Int,
    desc: String,
    image: String,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        // Weekly Subscription
        Text(
            text = titleSubs, style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                letterSpacing = 0.003.sp
            )
        )
        SpacerCustom(space = 5)
        // RP.50.000
        Text(
            text = priceSubs, style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                letterSpacing = 0.003.sp
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        )
        {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    painter = painterResource(id = R.drawable.history),
                    contentDescription = "sub_weekly",
                    modifier = modifier.size(20.dp)
                )
                SpacerCustom(space = 5)
                Text(
                    text = "$pickUpSubs Pick-up", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontSize = 15.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Button(onClick = {
                navController.navigate(
                    route = Screen.DetailSubscriptionScreen.route.replace(
                        "{image}", image
                    ).replace(
                        "{title}", titleSubs
                    ).replace(
                        "{pickup}", pickUpSubs.toString()
                    ).replace(
                        "{price}", priceSubs
                    ).replace(
                        "{description}", desc
                    )
                )

            }) {
                Text(
                    text = "See Details", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }
        }
    }
}