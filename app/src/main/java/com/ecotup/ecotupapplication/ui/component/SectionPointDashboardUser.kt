package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionPointDashboardUser(
    viewModel: HomeUserViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner
) {
    var point by remember {
        mutableStateOf("0")
    }

    viewModel.getSessionUser().observe(lifecycleOwner) {
        point = it.point
    }

    if (point == "" || point.isEmpty()) {
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
                contentDescription = stringResource(id = R.string.wallet_image),
                modifier = modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .border(
                        1.dp, Color.Gray, CircleShape
                    )
            )

            SpacerCustom(space = 5)

            Text(
                text = stringResource(id = R.string.your_points),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    letterSpacing = 0.003.sp
                )
            )

        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$point Points", style = MaterialTheme.typography.bodyMedium.copy(
                    color = GreenLight,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    letterSpacing = 0.003.sp
                )
            )
            SpacerCustom(space = 5)
        }
    }
}