package com.ecotup.ecotupapplication.ui.user.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.component.SectionAddressDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionArticleDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionMainMenuDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionPointDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionProfileDashboardUser
import com.ecotup.ecotupapplication.ui.component.SectionRemainingSubscriptionDasboardUser
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenUser(
    viewModel: HomeUserViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ), modifier: Modifier = Modifier, navigateToMaps: () -> Unit, navController : NavController
) {
    val context = LocalContext.current
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    var idUser by remember {
        mutableStateOf("")
    }

    viewModel.getSessionUser().observe(context as LifecycleOwner) {
        idUser = it.id
    }

    Box(modifier = modifier.fillMaxSize())
    {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(225.dp)
                .background(GreenLight, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        )
        {

        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            SpacerCustom(space = 10)
            Image(
                painter = painterResource(id = R.drawable.ecotup_logo_white_large),
                contentDescription = "logo_white",
                modifier = modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            SpacerCustom(space = 10)

            SectionProfileDashboardUser(
                viewModel = viewModel,
                lifecycleOwner = context as LifecycleOwner
            )

            SpacerCustom(space = 5)

            SectionAddressDashboardUser(
                viewModel = viewModel,
                lifecycleOwner = context as LifecycleOwner
            )

            SpacerCustom(space = 6)

            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                viewModel.getDetailUser(idUser).observe(context as LifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                swipeRefreshState.isRefreshing = true
                            }

                            is Result.Success -> {
                                val childResult = result.data.data
                                val id = childResult?.userId
                                val name = childResult?.userName
                                val email = childResult?.userEmail
                                val phone = childResult?.userPhone
                                val lat = childResult?.userLatitude
                                val long = childResult?.userLongitude
                                val profile = childResult?.userProfile
                                val point = childResult?.userPoint
                                val subscriptionDate = childResult?.userSubscriptionDate
                                val subscriptionStatus = childResult?.subscriptionStatus
                                val subscriptionValue = childResult?.subscriptionValue

                                viewModel.setSessionUser(
                                    PersonModelData(
                                        id = id.toString(),
                                        name = name.toString(),
                                        email = email.toString(),
                                        phone = phone.toString(),
                                        lat = lat.toString(),
                                        long = long.toString(),
                                        profile = profile.toString(),
                                        point = point.toString(),
                                        subscription_date = subscriptionDate.toString(),
                                        subscription_status = subscriptionStatus.toString(),
                                        subscription_value = subscriptionValue.toString()
                                    )
                                )
                                swipeRefreshState.isRefreshing = false
                            }

                            is Result.Error -> {
                                swipeRefreshState.isRefreshing = false
                            }

                            else -> {}
                        }
                    }
                }
            }) {
                LazyColumn(contentPadding = PaddingValues(bottom = 16.dp))
                {
                    item {
                        Column(modifier = modifier.clickable {
                            navController.navigate(Screen.RewardScreen.route)
                        }) {
                            SectionPointDashboardUser(
                                viewModel = viewModel,
                                lifecycleOwner = context as LifecycleOwner
                            )
                        }


                        SpacerCustom(space = 6)

                        SectionRemainingSubscriptionDasboardUser(
                            viewModel = viewModel,
                            lifecycleOwner = context as LifecycleOwner
                        )

                        SpacerCustom(space = 6)

                        SectionMainMenuDashboardUser(
                            navigateToMaps = navigateToMaps,
                            context = context
                        )

                        SpacerCustom(space = 6)
                        SectionArticleDashboardUser(viewModel = viewModel, lifecycleOwner = context as LifecycleOwner)
                    }
                }
            }
        }
    }
}
