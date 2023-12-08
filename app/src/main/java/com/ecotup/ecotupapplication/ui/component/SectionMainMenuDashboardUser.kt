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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionMainMenuDashboardUser(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
            MainMenu(modifier = modifier, image = painterResource(id = R.drawable.one_time), text = "One Time", contentDesc = "one_time")
            MainMenu(modifier = modifier, image = painterResource(id = R.drawable.weekly_subscription), text = "Weekly", contentDesc = "weekly_subscription")
            MainMenu(modifier = modifier, image = painterResource(id = R.drawable.monthly_subscription), text = "Monthly", contentDesc = "monthly_subscription")

    }
}

@Composable
fun MainMenu(modifier: Modifier = Modifier, image : Painter, text : String, contentDesc : String)
{
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = image,
            contentDescription = contentDesc,
            modifier = modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp)
                )
        )
        SpacerCustom(space = 3)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.003.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}