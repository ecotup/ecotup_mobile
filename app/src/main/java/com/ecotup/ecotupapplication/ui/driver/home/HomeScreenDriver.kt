package com.ecotup.ecotupapplication.ui.driver.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.DriverModelData
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository
import com.ecotup.ecotupapplication.data.response.DataItemUser
import com.ecotup.ecotupapplication.data.vmf.ServiceViewModelFactory
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.component.ListComponent
import com.ecotup.ecotupapplication.ui.component.SectionAddressDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionMainMenuDashboarDriver
import com.ecotup.ecotupapplication.ui.component.SectionPointDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionProfileDashboardDriver
import com.ecotup.ecotupapplication.ui.component.SectionStatusDriver
import com.ecotup.ecotupapplication.ui.component.oneTime
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.IntentToMapsDriverOneTime
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

private var idDriver = mutableStateOf("")
private var dataItemUser = mutableStateOf<List<DataItemUser>>(emptyList())
private var dataCluster0 = mutableStateOf<List<List<Any>>>(emptyList())
private var dataCluster1 = mutableStateOf<List<List<Any>>>(emptyList())
private var dataCluster2 = mutableStateOf<List<List<Any>>>(emptyList())

@Composable
fun HomeScreenDriver(
    viewModel: HomeDriverViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ),
    viewModel2: HomeDriverViewModel2 = viewModel(
        factory = ServiceViewModelFactory.getInstance(
            LocalContext.current
        )
    )

    , modifier: Modifier = Modifier, navController: NavController
) {
    val context = LocalContext.current
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    getIdDriver(viewModel, context)

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_doodle),
            contentDescription = "Background Home Screen",
            modifier = modifier.fillMaxSize()
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(225.dp)
                .background(GreenLight, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        ) {}


        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SpacerCustom(space = 10)

            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ecotup_logo_white_large),
                    contentDescription = "logo_white",
                    modifier = modifier.width(150.dp)

                )
                Row(modifier = modifier.align(Alignment.CenterEnd)) {
                    Image(painter = painterResource(id = R.drawable.notification),
                        contentDescription = "notification",
                        modifier = modifier
                            .size(20.dp)
                            .clickable {

                            })

                    SpacerCustom(space = 5)
                }
            }
            SpacerCustom(space = 15)
            SectionProfileDashboardDriver(
                viewModel = viewModel, lifecycleOwner = context as LifecycleOwner
            )
            SpacerCustom(space = 5)
            SectionAddressDashboardDriver(
                viewModel = viewModel, lifecycleOwner = context as LifecycleOwner
            )
            SpacerCustom(space = 6)

            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                getListDataUser(viewModel, context)
                viewModel.getDetailDriver(idDriver.value).observe(context as LifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                swipeRefreshState.isRefreshing = true
                            }

                            is Result.Success -> {
                                val childResult = result.data.data
                                val id = childResult?.driverId
                                val name = childResult?.driverName
                                val email = childResult?.driverEmail
                                val phone = childResult?.driverPhone
                                val lat = childResult?.driverLatitude
                                val long = childResult?.driverLongitude
                                val profile = childResult?.driverProfile
                                val point = childResult?.driverPoint
                                val license = childResult?.driverLicense
                                val type = childResult?.driverType
                                val rating = childResult?.driverRating

                                viewModel.setSessionDriver(
                                    DriverModelData(
                                        id.toString(),
                                        name.toString(),
                                        email.toString(),
                                        phone.toString(),
                                        lat.toString(),
                                        long.toString(),
                                        profile.toString(),
                                        if (point.toString() == "" || point.toString().isEmpty()) "0" else point.toString(),
                                        type.toString(),
                                        license.toString(),
                                        rating.toString()
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
                LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
                    item {
                        SectionPointDashboardDriver(
                            viewModel = viewModel, lifecycleOwner = context as LifecycleOwner
                        )
                        SpacerCustom(space = 10)
                        SectionStatusDriver()
                        SpacerCustom(space = 10)

                        Column(modifier) {
                            Column(
                                modifier = modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Job Driver",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = 16.sp,
                                        letterSpacing = 0.003.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                SpacerCustom(space = 10)

                                SectionMainMenuDashboarDriver(context = context)
                            }

                            SpacerCustom(space = 10)
                            if (oneTime.value) {
                                Text(
                                    text = "One Time",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = 16.sp,
                                        letterSpacing = 0.003.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GreenLight
                                    )
                                )
                            } else {
                                Text(
                                    text = "Subscription",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = 16.sp,
                                        letterSpacing = 0.003.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GreenLight
                                    )
                                )
                            }
                            SpacerCustom(space = 10)
                        }
                    }

                    getListDataUser(viewModel, context)
                    getListDataUserSubscription(viewModel2, context)
                    if (oneTime.value) {
                        if (dataItemUser.value.isNullOrEmpty()) {
                            item {
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.no_order),
                                        contentDescription = "No Order",
                                        modifier = modifier.size(150.dp)
                                    )
                                }
                            }
                        } else {
                            items(dataItemUser.value, key = { it.transactionId }) { item ->
                                var imageUser = ""
                                viewModel.getDetailUser(item.userId.toString())
                                    .observe(context) { resultUser ->
                                        if (resultUser != null) {
                                            when (resultUser) {
                                                is Result.Loading -> {
                                                    Log.d("HOME SCREEN DRIVER", "Loading")
                                                }

                                                is Result.Success -> {
                                                    imageUser =
                                                        resultUser.data.data?.userProfile.toString()
                                                }

                                                is Result.Error -> {
                                                    Log.d("HOME SCREEN DRIVER", "Error")
                                                }
                                            }
                                        }
                                    }
                                ListComponent(image = imageUser,
                                    lat = item.transactionLatitudeDestination as Double,
                                    long = item.transactionLongitudeDestination as Double,
                                    context = context,
                                    onClickItem = {
                                        // Intent to Maps
                                        IntentToMapsDriverOneTime(
                                            context = context,
                                            idTransaksi = item.transactionId.toString()
                                        )
                                    })
                                Log.d("ID", "${item.transactionId.toString()}")
                                SpacerCustom(space = 5)
                            }
                        }
                    } else {
                        // Subscription

                        if (idDriver.value.toInt() == 2) {
                            // cluster 0
                            items(dataCluster0.value, key = {it[0]}){cluster0 ->
                                var imageUser = ""
                                viewModel.getDetailUser(cluster0[4].toString())
                                    .observe(context) { resultUser ->
                                        if (resultUser != null) {
                                            when (resultUser) {
                                                is Result.Loading -> {
                                                    Log.d("HOME SCREEN DRIVER", "Loading")
                                                }

                                                is Result.Success -> {
                                                    imageUser =
                                                        resultUser.data.data?.userProfile.toString()
                                                }

                                                is Result.Error -> {
                                                    Log.d("HOME SCREEN DRIVER", "Error")
                                                }
                                            }
                                        }
                                    }
                                ListComponent(image = imageUser,
                                    lat = cluster0[6] as Double,
                                    long = cluster0[5] as Double,
                                    context = context,
                                    onClickItem ={
                                    })
                                Log.d("ID", "${cluster0[0].toString()}")
                                SpacerCustom(space = 5)

                            }
                        } else if (idDriver.value.toInt() == 3) {
                            // CLuster 1
                            items(dataCluster1.value, key = {it[0]}){cluster1 ->
                                var imageUser = ""
                                viewModel.getDetailUser(cluster1[4].toString())
                                    .observe(context) { resultUser ->
                                        if (resultUser != null) {
                                            when (resultUser) {
                                                is Result.Loading -> {
                                                    Log.d("HOME SCREEN DRIVER", "Loading")
                                                }

                                                is Result.Success -> {
                                                    imageUser =
                                                        resultUser.data.data?.userProfile.toString()
                                                }

                                                is Result.Error -> {
                                                    Log.d("HOME SCREEN DRIVER", "Error")
                                                }
                                            }
                                        }
                                    }
                                ListComponent(image = imageUser,
                                    lat = cluster1[6] as Double,
                                    long = cluster1[5] as Double,
                                    context = context,
                                    onClickItem = {
                                    })
                                Log.d("ID", "${cluster1[0].toString()}")
                                SpacerCustom(space = 5)

                            }
                        } else if (idDriver.value.toInt() == 4) {
                            // Cluster 2
                            items(dataCluster2.value, key = {it}){cluster2 ->
                                var imageUser = ""
                                viewModel.getDetailUser(cluster2[4].toString())
                                    .observe(context) { resultUser ->
                                        if (resultUser != null) {
                                            when (resultUser) {
                                                is Result.Loading -> {
                                                    Log.d("HOME SCREEN DRIVER", "Loading")
                                                }

                                                is Result.Success -> {
                                                    imageUser =
                                                        resultUser.data.data?.userProfile.toString()
                                                }

                                                is Result.Error -> {
                                                    Log.d("HOME SCREEN DRIVER", "Error")
                                                }
                                            }
                                        }
                                    }
                                ListComponent(image = imageUser,
                                    lat = cluster2[6] as Double,
                                    long = cluster2[5] as Double,
                                    context = context,
                                    onClickItem = {
                                    })
                                SpacerCustom(space = 5)
                                Log.d("ID", "${cluster2[0].toString()}")
                            }
                        } else {
                            item {
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.no_order),
                                        contentDescription = "No Order",
                                        modifier = modifier.size(150.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


private fun getIdDriver(viewModel: HomeDriverViewModel, context: Context) {
    viewModel.getSessionDriver().observe(context as LifecycleOwner) {
        idDriver.value = it.id
    }
}

private fun getListDataUser(viewModel: HomeDriverViewModel, context: Context) {
    viewModel.getJobDriverOnGoingOneTime(idDriver = idDriver.value)
        .observe(context as LifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        Log.d("HOME SCREEN DRIVER", "Loading")
                    }

                    is Result.Success -> {
                        Log.d("HOME SCREEN DRIVER", "Success")
                        val data = result.data.data
                        dataItemUser.value = data

                        Log.d(
                            "HOME SCREEN DRIVER", data?.get(0)?.transactionId.toString()
                        )
                        Log.d(
                            "HOME SCREEN DRIVER", data?.get(1)?.transactionId.toString()
                        )
                    }

                    is Result.Error -> {
                        Log.d("HOME SCREEN DRIVER", "Error")
                    }
                }
            }
        }
}


private fun getListDataUserSubscription(viewModel2: HomeDriverViewModel2, context: Context) {
    viewModel2.getClusteringAndSorting().observe(context as LifecycleOwner) { result ->
        if (result != null) {
            when (result) {
                is Result.Loading -> {
                    Log.d("HOME SCREEN DRIVER", "Loading")
                }

                is Result.Success -> {
                    Log.d("HOME SCREEN DRIVER", "Success")
                    val data = result.data
                    dataCluster0.value = data.cluster0
                    dataCluster1.value = data.cluster1
                    dataCluster2.value = data.cluster2

                }

                is Result.Error -> {
                    Log.d("HOME SCREEN DRIVER", "Error")
                }
            }
        }
    }

}

