package com.ecotup.ecotupapplication.ui.user.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.component.CardRewardUser
import com.ecotup.ecotupapplication.ui.component.SectionHistoryUser
import com.ecotup.ecotupapplication.ui.component.SectionIncomeDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionPointDashboardDriver
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun RewardScreen(modifier : Modifier = Modifier, navController: NavController) {

    Box(modifier = modifier.fillMaxSize())
    {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(65.dp)
                .background(GreenLight, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        ) {

        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                val painterBack = painterResource(id = R.drawable.button_back_white)
                ClickableImageBack(
                    painter = painterBack,
                    contentDescription = "Back",
                    onClick = {
                        navController.popBackStack()
                    },
                    35,
                    35
                )
                Image(
                    painter = painterResource(id = R.drawable.reward_icon),
                    contentDescription = "time_icon_reward",
                    modifier = modifier.size(22.dp),
                )
                SpacerCustom(space = 5)
                Text(
                    text = "Reward", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            SpacerCustom(space = 10)
            Text(text = "Come on, exchange your points !", style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                letterSpacing = 0.003.sp
            ))
            SpacerCustom(space = 5)

            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp))
            {
                items(10) { index ->
                    CardRewardUser(navController = navController)
                    SpacerCustom(space = 5)
                }
            }

        }
    }
}