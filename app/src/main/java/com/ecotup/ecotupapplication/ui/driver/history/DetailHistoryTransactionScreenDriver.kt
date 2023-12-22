package com.ecotup.ecotupapplication.ui.driver.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.component.CardDriverHistoryTransaction
import com.ecotup.ecotupapplication.ui.component.CardPointEarnedUser
import com.ecotup.ecotupapplication.ui.component.CardTripDetails
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun DetailHistoryTransactionScreenDriver(
    modifier: Modifier = Modifier, navController: NavController
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Button Back
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                val painterBack = painterResource(id = R.drawable.button_back)
                ClickableImageBack(
                    painter = painterBack,
                    contentDescription = "Back",
                    onClick = {
                        navController.popBackStack()
                    },
                    35,
                    35
                )
                Text(
                    text = "Transaction Detail",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
            }
            SpacerCustom(space = 25)

            CardDriverHistoryTransaction()

            SpacerCustom(space = 10)

            CardTripDetails()

            SpacerCustom(space = 10)

            PaymentDetails()

            SpacerCustom(space = 5)

            Image(
                painter = painterResource(id = R.drawable.one_time),
                contentDescription = "one_time",
                modifier
                    .size(54.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            SpacerCustom(space = 5)

            CardPointEarnedUser()

            SpacerCustom(space = 10)

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = modifier.widthIn(150.dp, 240.dp)
                ) {
                    Text(
                        text = "Report", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, fontSize = 15.sp, letterSpacing = 0.003.sp
                        )
                    )
                }

                SpacerCustom(space = 10)

                Button(onClick = { /*TODO*/ }, modifier = modifier.widthIn(150.dp, 240.dp)) {
                    Text(
                        text = "Review", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, fontSize = 15.sp, letterSpacing = 0.003.sp
                        )
                    )
                }
            }


        }
    }
}

@Composable
fun PaymentDetails(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Payment Details",
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Bold
            )
        )
        SpacerCustom(space = 10)
        // Transaction date
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Transaction date",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Oct 28, 2023 10:30 AM",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }
        SpacerCustom(space = 5)
        // ID transaction
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ID transaction",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "ECO-111004",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }
        SpacerCustom(space = 5)
        // Total weight
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total weight",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "5kg",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }
        SpacerCustom(space = 5)
        // Total payment
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total payment",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Rp. 10.000",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

//@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
//@Composable
//fun DetailHistoryScreenPrev() {
//    DetailHistoryTransactionScreen()
//}