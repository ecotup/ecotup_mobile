package com.ecotup.ecotupapplication.ui.component

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.theme.Orange
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.SpacerCustom
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SectionRemainingSubscriptionDasboardUser(
    viewModel: HomeUserViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner
) {
    val currentTimeNow: Instant = Instant.now()

    var dateSubs by remember {
        mutableStateOf("")
    }

    var statusSubs by remember {
        mutableStateOf("")
    }

    var valueSubs by remember {
        mutableStateOf("0")
    }

    viewModel.getSessionUser().observeForever {
        dateSubs = it.subscription_date
        statusSubs = it.subscription_status
        valueSubs = it.subscription_value
    }

    Log.d("dateSubs", "$dateSubs $valueSubs")

    if (dateSubs != null && dateSubs != "" && dateSubs != "null" && statusSubs != null && statusSubs != "" && statusSubs != "null") {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        val dateTimeNow = LocalDateTime.parse(currentTimeNow.toString(), formatter)
        val dateTimeBuySubs = LocalDateTime.parse(dateSubs, formatter)

        val instant1 = dateTimeBuySubs.toInstant(ZoneOffset.UTC)
        val instant2 = dateTimeNow.toInstant(ZoneOffset.UTC)

        val daysDiff = ChronoUnit.DAYS.between(instant1, instant2)
        val tempValue = when (valueSubs) {
            "" -> 0
            null -> 0
            else -> valueSubs.toInt()
        }
        val remainingDays = tempValue - daysDiff.toInt()

        // status subs
        val statusDisplay = when (statusSubs) {
            "Monthly" -> {
                "Monthly Subscription"
            }

            "Weekly" -> {
                "Weekly Subscription"
            }

            else -> {
                "No Subscription"
            }
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
                Text(
                    text = statusDisplay, style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        letterSpacing = 0.003.sp
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$remainingDays Days", style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (remainingDays <= 3) Color.Red else if (remainingDays in 4..7) Orange else GreenLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        letterSpacing = 0.003.sp
                    )
                )
                SpacerCustom(space = 5)
            }
        }
    }
}