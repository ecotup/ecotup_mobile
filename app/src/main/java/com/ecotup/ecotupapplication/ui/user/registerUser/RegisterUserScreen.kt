package com.ecotup.ecotupapplication.ui.user.registerUser


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ButtonGoogle
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.getReadableLocation
import com.ecotup.ecotupapplication.util.sweetAlert
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@Composable
fun RegisterUserScreen(
    viewModel: RegisterUserViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    navController: NavController,
    modifier: Modifier = Modifier

) {
    val context = LocalContext.current
    Box(
        modifier = modifier
    ) {
        // Background
        val imageBackground = painterResource(id = R.drawable.background_doodle)
        Image(painter = imageBackground, contentDescription = "Background Ecotup")

        // Button Back
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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
            // Register Form
            RegisterForm(modifier = modifier, context = context, navController)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterForm(modifier: Modifier, context: Context, navController: NavController) {
    var textEmail by remember {
        mutableStateOf("")
    }
    var textFullname by remember {
        mutableStateOf("")
    }
    var isEmailValid by remember {
        mutableStateOf(false)
    }
    var textPhoneNumber by remember {
        mutableStateOf("")
    }

    var lat by remember {
        mutableDoubleStateOf(0.0)
    }

    var long by remember {
        mutableDoubleStateOf(0.0)
    }


    LazyColumn(
        modifier = modifier
    ) {

        item {
            Column(modifier = modifier.padding(16.dp)) {
                Box(
                    modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Register", style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 35.sp, color = GreenLight, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "User's biodata",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }
                SpacerCustom(space = 20)

                // Fullname
                Text(
                    text = "Fullname",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = textFullname,
                    onValueChange = {
                        textFullname = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your fullname",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )

                SpacerCustom(space = 10)

                // Email
                Text(
                    text = "Email",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = textEmail,
                    onValueChange = {
                        textEmail = it
                        isEmailValid = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    },
                    placeholder = {
                        Text(
                            text = "Input your email address",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )

                SpacerCustom(space = 10)

                // Phone Number
                Text(
                    text = "Phone Number",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = textPhoneNumber,
                    onValueChange = {
                        textPhoneNumber = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your phone number",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )

                SpacerCustom(space = 10)

                // Address
                Column {
                    Text(
                        text = "Find My Address",
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
                Button(modifier = modifier.align(Alignment.End), onClick = {
                    if (textFullname.isEmpty() || textEmail.isEmpty() || textPhoneNumber.isEmpty() || lat == 0.0 || long == 0.0) {
                        sweetAlert(
                            context = context,
                            title = "Warning",
                            contentText = "Please fill all the form",
                            type = "warning", isCancel = true
                        )
                    } else {
                        if (textPhoneNumber.length in 11..13) {
                            navController.navigate(
                                route = Screen.RegisterUserScreenPassword.route.replace(
                                    "{name}", textFullname
                                ).replace(
                                    "{email}", textEmail
                                ).replace(
                                    "{phone}", textPhoneNumber
                                ).replace(
                                    "{lat}", lat.toString()
                                ).replace(
                                    "{long}", long.toString()
                                )
                            )
                        } else {
                            sweetAlert(
                                context = context,
                                title = "Warning",
                                contentText = "Phone number at least 11 until max 13 digits",
                                type = "warning", isCancel = true
                            )
                        }
                    }
                }) {
                    Text(
                        text = "Next", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, letterSpacing = 0.003.sp
                        )
                    )
                }
            }

            // Google
            Column(
                modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = modifier,
                    text = "Register with",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = GreenLight, fontSize = 15.sp, textAlign = TextAlign.Right
                    ),
                )
                SpacerCustom(space = 5)
                ButtonGoogle(
                    image = R.drawable.google_logo,
                    text = "Google",
                    modifier = modifier,
                    click = {})
                SpacerCustom(space = 5)
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Text(
                        modifier = modifier,
                        text = "Have an account ?",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight, fontSize = 15.sp, textAlign = TextAlign.Right
                        ),
                    )
                    Text(
                        modifier = modifier
                            .clickable {
                                navController.navigate(Screen.AuthScreen.route)
                            },
                        text = "Log In",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = GreenLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Right,
                            textDecoration = TextDecoration.Underline
                        ),
                    )
                }
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