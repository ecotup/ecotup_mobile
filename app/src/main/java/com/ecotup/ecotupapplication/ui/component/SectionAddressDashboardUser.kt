package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionAddressDashboardUser(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        SpacerCustom(space = 3)
        // Point Address
        Image(
            painter = painterResource(id = R.drawable.point_address),
            contentDescription = "point_address",
            modifier = modifier.size(22.dp)
        )
        SpacerCustom(space = 5)

        // Address
        Text(
            text = "Jl. Jendral Sudirman, No. 20 (Rumah Ecotup Team)",
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 0.003.sp
            )
        )
    }


}