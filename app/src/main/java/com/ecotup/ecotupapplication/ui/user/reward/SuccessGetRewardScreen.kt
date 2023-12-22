package com.ecotup.ecotupapplication.ui.user.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun SuccessGetRewardScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Yeah, you sucessfull get reward",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = GreenLight,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.003.sp
            )
        )
        SpacerCustom(space = 5)
        Text(
            text = "Tote Bag", style = MaterialTheme.typography.bodyMedium.copy(
                color = GreenLight,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.003.sp
            )
        )
        SpacerCustom(space = 30)

        Image(
            painter = painterResource(id = R.drawable.tote_bag_image),
            contentDescription = "reward_tote_bag",
            modifier = modifier.size(184.dp)
        )

        SpacerCustom(space = 60)

        Button(
            onClick = {
                navController.navigate(Screen.HomeScreenUser.route)
            }, modifier = modifier
                .fillMaxWidth()
                .height(42.dp)
        ) {
            Text(
                text = "Back to Reward", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, fontSize = 15.sp, letterSpacing = 0.003.sp
                )
            )
        }
    }
}
