package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionRemainingSubscriptionDasboardUser(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            SpacerCustom(space = 5)
            Text(
                text = "Monthly Subscription", style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    letterSpacing = 0.003.sp
                )
            )

        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "29 Days", style = MaterialTheme.typography.bodyMedium.copy(
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