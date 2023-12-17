package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.driver.home.HomeDriverViewModel
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionPointDashboardDriver(
    viewModel: HomeDriverViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner
) {
    var point by remember {
        mutableStateOf("")
    }

    var vehicle by remember {
        mutableStateOf("")
    }

    var vehiclePlate by remember {
        mutableStateOf("")
    }

    viewModel.getSessionDriver().observe(lifecycleOwner) {
        point = it.point
        vehicle = it.type
        vehiclePlate = it.license
    }

    if (point == "" || point.isEmpty() || point == null) {
        point = "0"
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            SpacerCustom(space = 5)
            Image(
                painter = painterResource(id = R.drawable.wallet_image),
                contentDescription = "wallet_image",
                modifier = modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .border(
                        1.dp, Color.Gray, CircleShape
                    )
            )

            SpacerCustom(space = 5)

            Column{
                Text(
                    text = "Your Points", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        letterSpacing = 0.003.sp
                    )
                )
                SpacerCustom(space = 2)
                Text(
                    text = "$point Points", style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }


        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .background(
                GreenLight, RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 5.dp)) {
            Column (modifier= modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$vehicle", style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.003.sp
                    )
                )
                SpacerCustom(space = 2)
                Text(
                    text = "$vehiclePlate", style = MaterialTheme.typography.bodyMedium.copy(
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

