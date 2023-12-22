package com.ecotup.ecotupapplication.ui.user.editAddressUser

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.getReadableLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@Composable
fun EditAddressScreenUser(
    modifier: Modifier = Modifier, navController: NavController
) {
    val context = LocalContext.current
    Box(modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp)) {

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
                    text = "Edit Address",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        EditAddressForm(modifier = Modifier, context = context)
    }
}

@Composable
fun EditAddressForm(modifier: Modifier, context: Context) {
    var lat by remember {
        mutableDoubleStateOf(0.0)
    }

    var long by remember {
        mutableDoubleStateOf(0.0)
    }

    LazyColumn {
        item {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                SpacerCustom(space = 40)

                // Address
                Column {
                    Text(
                        text = "Edit My Address",
                        modifier = modifier,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                        )
                    )
                    SpacerCustom(space = 3)
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            val imageLocation =
                                painterResource(id = R.drawable.location_node_90_x_90)
                            Image(
                                painter = imageLocation,
                                contentDescription = "Location",
                                modifier = modifier.size(22.dp)
                            )
                            SpacerCustom(space = 5)
                            Text(
                                text = getReadableLocation(lat, long, context),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 15.sp, color = Color.Black
                                )
                            )
                        }

                        //
                        val fusedLocationClient: FusedLocationProviderClient by remember {
                            mutableStateOf(LocationServices.getFusedLocationProviderClient(context))
                        }

                        val requestPermissionLauncher =
                            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                                if (isGranted) {
                                    Log.d("Permission", "Granted")
                                } else {
                                    Log.d("Permission", "Not Granted")
                                }
                            }

                        Button(
                            onClick = {
                                if (ContextCompat.checkSelfPermission(
                                        context, Manifest.permission.ACCESS_FINE_LOCATION
                                    ) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    if (isLocationEnabled(context)) {
                                        fusedLocationClient.lastLocation.addOnSuccessListener { loc: android.location.Location? ->
                                            loc?.let {
                                                Log.i(
                                                    "LOCATION FUNCTION",
                                                    "Lat: ${loc.latitude}, Lon: ${loc.longitude}"
                                                )
                                                lat = loc.latitude
                                                long = loc.longitude
                                            } ?: run {
                                                Log.d("LOCATION FUNCTION", "Location is null")
                                            }
                                        }
                                    } else {
                                        Log.d("Permission", "Location not enabled")
                                    }
                                } else {
                                    Log.d("Permission", "Requesting permission...")
                                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                                }
                            }, modifier = Modifier
                                .border(
                                    1.dp, color = GreenLight, shape = MaterialTheme.shapes.small
                                )
                                .height(35.dp), colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White, contentColor = GreenLight
                            )
                        ) {
                            Text(text = "Find Now")
                        }
                    }
                }

                SpacerCustom(space = 10)
                // Button Next
//                Button(modifier = modifier
//                    .fillMaxWidth(), onClick = {}
//                ) {
//                    Text(
//                        text = "Save Changed", style = MaterialTheme.typography.bodyMedium.copy(
//                            fontWeight = FontWeight.Bold, letterSpacing = 0.003.sp
//                        )
//                    )
//                }

            }

        }
    }

}

private fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    return isGpsEnabled || isNetworkEnabled
}

@Preview(showBackground = true, device = Devices.PIXEL_2_XL)
@Composable
fun EditProfileScreenUserPrev() {
    EditAddressScreenUser(navController = rememberNavController())
}