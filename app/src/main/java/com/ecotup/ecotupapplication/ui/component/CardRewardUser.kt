package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.response.DataReward
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun CardRewardUser(
    modifier: Modifier = Modifier,
    navController: NavController,
    rewardItem: DataReward
) {
    Column(
        modifier = modifier
            .width(160.dp)
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.background_doodle),
                contentDescription = stringResource(R.string.background_doodle),
                modifier = modifier
                    .size(160.dp, 120.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            )

            Image(
                painter = rememberAsyncImagePainter(model = rewardItem.rewardImage.toString()),
                contentDescription = "reward_image",
                modifier = modifier
                    .size(80.dp, 80.dp)
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerCustom(space = 2)

            Text(
                text = rewardItem.rewardName.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = GreenLight,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 0.003.sp
                )
            )

            SpacerCustom(space = 5)

            Text(
                text = stringResource(R.string.points, rewardItem.rewardPrice.toString()),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.003.sp
                )
            )

            SpacerCustom(space = 5)

            Button(onClick = {
                navController.navigate(
                    route = Screen.DetailRewardScreen.route.replace(
                        "{iR}", newValue = rewardItem.rewardImage.toString()
                    ).replace(
                        "{nR}", newValue = rewardItem.rewardName.toString()
                    ).replace(
                        "{pR}", newValue = rewardItem.rewardPrice.toString()
                    ).replace(
                        "{dR}", newValue = rewardItem.rewardDescription.toString()
                    )
                )

            }, modifier = modifier.size(125.dp, 32.dp)) {
                Text(
                    text = stringResource(R.string.detail),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 0.003.sp
                    )
                )
            }
            SpacerCustom(space = 10)
        }
    }
}