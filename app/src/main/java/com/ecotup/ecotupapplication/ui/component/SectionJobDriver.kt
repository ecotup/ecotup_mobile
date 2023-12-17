package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.util.SpacerCustom


@Composable
fun SectionJobDriver(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(modifier)
    {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "Job Driver", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp, letterSpacing = 0.003.sp, fontWeight = FontWeight.Bold
                )
            )

            SpacerCustom(space = 10)

            SectionMainMenuDashboarDriver(context = context)
        }





        SpacerCustom(space = 10)

        Text(
            text = "One Time", style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp, letterSpacing = 0.003.sp, fontWeight = FontWeight.Bold
            )
        )
        SpacerCustom(space = 5)

        for (i in 0..20) {
            ListComponent(modifier = modifier)
            SpacerCustom(space = 5)
        }

    }
}

@Composable
private fun ListComponent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_temp),
            contentDescription = "profile",
            modifier = Modifier
                .size(30.dp)
                .clip(
                    CircleShape
                )
        )
        SpacerCustom(space = 5)
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                painter = painterResource(id = R.drawable.location_node_90_x_90),
                contentDescription = "profile",
                modifier = Modifier.size(20.dp)
            )
            SpacerCustom(space = 10)
            Text(
                text = "Jl. Raya Bogor KM 30",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                )
            )
        }
    }
}