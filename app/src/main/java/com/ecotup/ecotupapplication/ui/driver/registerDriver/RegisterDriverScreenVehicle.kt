package com.ecotup.ecotupapplication.ui.driver.registerDriver

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.sweetAlert

@Composable
fun RegisterDriverScreenVehicle(
    viewModel: RegisterDriverViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ),
    navController: NavController,
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    phone: String,
    lat: Double,
    long: Double,
) {
    // Context
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

            // Register Form Vehicle
            RegisterVehicle(modifier = modifier, context = context, navController = navController, name = name, email = email, phone = phone, lat = lat, long = long)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterVehicle(modifier: Modifier, context: Context, navController: NavController, name: String, email: String, phone: String, lat: Double, long: Double) {
    var textNumberVehicle by remember {
        mutableStateOf("")
    }


    var vehicleType by remember {
        mutableStateOf("")
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
                            text = "Driver's vehicle",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }

                SpacerCustom(space = 20)


                // Number of vehicles
                Text(
                    text = "Number of vehicles",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = textNumberVehicle,
                    onValueChange = {
                        textNumberVehicle = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your number of vehicles",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )

                SpacerCustom(space = 10)

                // Vehicles Type
                Text(
                    text = "Vehicles Type",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = vehicleType,
                    onValueChange = {
                        vehicleType = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your vehicles type",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )

                SpacerCustom(space = 30)

                // Button Next
                Button(modifier = modifier.align(Alignment.End), onClick = {
                    if (textNumberVehicle.isEmpty() || vehicleType.isEmpty()) {
                        sweetAlert(
                            context = context,
                            title = "Warning",
                            contentText = "Please fill all the form",
                            type = "warning", isCancel = true)
                    }
                    else
                    {
                        navController.navigate(
                            route = Screen.RegisterDriverScreenPassword.route.replace(
                                "{name}", name
                            ).replace(
                                "{email}", email
                            ).replace(
                                "{phone}", phone
                            ).replace(
                                "{lat}", lat.toString()
                            ).replace(
                                "{long}", long.toString()
                            ).replace(
                                "{type}", vehicleType
                            ).replace(
                                "{license}", textNumberVehicle
                            )
                        )
                    }

                }) {
                    Text(
                        text = "Next", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, letterSpacing = 0.003.sp
                        )
                    )
                }
            }
            SpacerCustom(space = 20)
            Row(modifier = modifier.padding(20.dp), horizontalArrangement = Arrangement.Center) {
                LogoEcotup(modifier = modifier)
            }
        }
    }
}

@Composable
private fun LogoEcotup(modifier: Modifier) {
    val imageEcotup = R.drawable.ecotup_logo_small
    val painterEcotup = painterResource(imageEcotup)
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterEcotup,
            contentDescription = "Logo Ecotup",
            modifier = modifier.width(250.dp)
        )
    }
}

