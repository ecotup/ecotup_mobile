package com.ecotup.ecotupapplication.ui.user.subscription

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.sweetAlert
import kotlinx.coroutines.runBlocking

@Composable
fun DetailSubscriptionScreen(
    viewModel: SubscriptionUserViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ),
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context,
    image: String,
    title: String,
    pickup: String,
    price: String,
    description: String,
) {
    var idUser by remember {
        mutableStateOf("")
    }

    viewModel.getSessionUser().observe(context as LifecycleOwner)
    {
        idUser = it.id
    }

    Box(modifier = modifier.fillMaxSize())
    {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val painterBack = painterResource(id = R.drawable.button_back)
                ClickableImageBack(
                    painter = painterBack,
                    contentDescription = "Back",
                    onClick = { navController.popBackStack() },
                    35,
                    35
                )
                Text(
                    text = "Back",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
            }

            LazyColumn {
                item {
                    SpacerCustom(space = 20)
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = if (image == "weekly") painterResource(id = R.drawable.weekly_subscription_transparent) else painterResource(
                                id = R.drawable.monthly_subscription_transparent
                            ), contentDescription = "subscription", modifier = modifier.size(200.dp)
                        )
                    }

                    SpacerCustom(space = 20)

                    Column {
                        Text(
                            text = title, style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )

                        SpacerCustom(space = 5)

                        Text(
                            text = price, style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = GreenLight
                            )
                        )
                    }

                    SpacerCustom(space = 10)

                    Column {
                        Text(
                            text = "Description", style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )

                        SpacerCustom(space = 3)

                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Justify
                            )
                        )
                    }

                    SpacerCustom(space = 10)

                    Column(modifier = modifier) {
                        Text(
                            text = "Benefit", style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )

                        SpacerCustom(space = 3)

                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .border(1.dp, color = Color.Gray.copy(alpha = 0.5f), shape = MaterialTheme.shapes.medium.copy(
                                    topStart = CornerSize(16.dp),
                                    topEnd = CornerSize(16.dp),
                                    bottomStart = CornerSize(16.dp),
                                    bottomEnd = CornerSize(16.dp)
                                ))
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Image(
                                painter = painterResource(id = R.drawable.benefit),
                                contentDescription = "benefit", modifier = modifier.size(20.dp)
                            )

                            SpacerCustom(space = 10)

                            Text(
                                text = "$pickup Pick-up",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Justify
                                )
                            )
                        }

                    }

                    val subscriptionId = when (image) {
                        "weekly" -> 2
                        "monthly" -> 3
                        else -> 1
                    }

                    SpacerCustom(space = 10)

                    Button(onClick = {
                        updateSubs(
                            id = idUser,
                            subscriptionId = subscriptionId.toString(),
                            viewModel = viewModel,
                            lifecycleOwner = context as LifecycleOwner,
                            title = title,
                            navController = navController
                        )
                    }, modifier = modifier.align(Alignment.End)) {
                        Text(
                            text = "Buy Now", style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }
                }
            }
        }
    }
}


private fun updateSubs(
    id: String,
    subscriptionId: String,
    viewModel: SubscriptionUserViewModel,
    lifecycleOwner: LifecycleOwner,
    title: String,
    navController: NavController
) {
    runBlocking {
        viewModel.updateSubscription(id, subscriptionId).observe(lifecycleOwner)
        { result ->
            when (result) {
                is Result.Loading -> {}
                is Result.Success -> {
                    val response = result.data.message
                    val sweetAlertDialog =
                        SweetAlertDialog(lifecycleOwner as Context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).setCustomImage(R.drawable.ask)
                            .setTitleText("Will you buy a $title ?")
                            .setConfirmButton("OK") {
                                val sweetAlertDialog =
                                    SweetAlertDialog(lifecycleOwner as Context, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Congratulations !")
                                        .setContentText("Successfully purchasing a $title, Please refresh your page !")
                                        .setConfirmButton("OK") {child ->
                                            child.dismissWithAnimation()
                                            // Insert 7 / 30 ke transaksi
                                            navController.navigate(Screen.HomeScreenUser.route)
                                        }
                                sweetAlertDialog.setCancelable(true)
                                sweetAlertDialog.show()
                                it.dismissWithAnimation()
                            }
                            .setCancelButton("Cancel") {
                                it.dismissWithAnimation()
                            }
                    sweetAlertDialog.setCancelable(true)
                    sweetAlertDialog.show()
                }

                is Result.Error -> {}

            }

        }
    }


}
