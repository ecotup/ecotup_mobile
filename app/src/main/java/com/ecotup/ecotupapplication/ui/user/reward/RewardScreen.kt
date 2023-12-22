package com.ecotup.ecotupapplication.ui.user.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.response.DataReward
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.component.CardRewardUser
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun RewardScreen(
    viewModel: RewardViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ), modifier: Modifier = Modifier, navController: NavController
) {

    val context = LocalContext.current

    var rewardList by remember { mutableStateOf<List<DataReward>>(emptyList()) }

    LaunchedEffect(viewModel) {
        viewModel.getReward().observe(context as LifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    rewardList = result.data.data
                }

                else -> {

                }
            }
        }
    }

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
            ) {
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
            Text(
                text = "Come on, exchange your points !",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    letterSpacing = 0.003.sp
                )
            )
            SpacerCustom(space = 5)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            )
            {
                items(rewardList, key = { it.rewardId!! }) { item ->
                    CardRewardUser(navController = navController, rewardItem = item)
                    SpacerCustom(space = 5)
                }
            }

        }
    }
}