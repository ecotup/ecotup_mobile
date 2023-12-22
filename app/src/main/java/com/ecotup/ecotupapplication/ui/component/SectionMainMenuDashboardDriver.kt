package com.ecotup.ecotupapplication.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.oneTime

@Composable
fun SectionMainMenuDashboarDriver(
    modifier: Modifier = Modifier,
    context: Context
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.one_time),
                contentDescription = "one_time",
                modifier = modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        oneTime.value = true
                    }
            )
            SpacerCustom(space = 3)
            Text(
                text = stringResource(id = R.string.one_time),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (oneTime.value) GreenLight else Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.003.sp,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.subs),
                contentDescription = stringResource(id = R.string.subscription),
                modifier = modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        oneTime.value = false
                    }

            )
            SpacerCustom(space = 3)
            Text(
                text = stringResource(id = R.string.subscription),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (!oneTime.value) GreenLight else Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.003.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
