package com.ecotup.ecotupapplication.ui.driver.editProfileDriver

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FireTruck
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.DriverModelData
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.sweetAlert
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.runBlocking

@Composable
fun EditProfileScreenDriver(
    viewModel: EditDriverViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ), modifier: Modifier = Modifier, navController: NavController
) {
    val context = LocalContext.current
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    var idDriver by remember {
        mutableStateOf("")
    }

    viewModel.getSessionDriver().observe(context as LifecycleOwner) {
        idDriver = it.id
    }

    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
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
                    text = "Edit Profile",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        SwipeRefresh(state = swipeRefreshState, onRefresh = {
            viewModel.getDetailDriver(idDriver).observe(context as LifecycleOwner) { result ->
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
                            val type = childResult?.driverType
                            val license = childResult?.driverLicense
                            val rating = childResult?.driverRating

                            viewModel.setSessionDriver(
                                DriverModelData(
                                    id = id.toString(),
                                    name = name.toString(),
                                    email = email.toString(),
                                    phone = phone.toString(),
                                    lat = lat.toString(),
                                    long = long.toString(),
                                    profile = profile.toString(),
                                    point = point.toString(),
                                    type = type.toString(),
                                    license = license.toString(),
                                    rating = rating.toString()
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
            EditProfileFormDriver(
                idDriver = idDriver,
                viewModel = viewModel,
                modifier = Modifier,
                lifecycleOwner = context as LifecycleOwner,
                context = context,
                navController = navController
            )
        }
    }
}

@Composable
fun EditProfileFormDriver(
    idDriver: String,
    viewModel: EditDriverViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner, context: Context, navController: NavController
) {
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var isEmailValid by remember {
        mutableStateOf(false)
    }
    var phone by remember {
        mutableStateOf("")
    }
    var type by remember {
        mutableStateOf("")
    }
    var license by remember {
        mutableStateOf("")
    }

    var photoProfile by remember {
        mutableStateOf("")
    }

    LaunchedEffect(viewModel)
    {
        viewModel.getSessionDriver().observe(lifecycleOwner) {
            name = it.name
            email = it.email
            phone = it.phone
            type = it.type
            license = it.license
            photoProfile = it.profile
        }
    }

    LazyColumn {
        item {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                SpacerCustom(space = 40)
                Text(
                    text = "DRIVER", style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold
                    ), modifier = modifier.align(Alignment.CenterHorizontally)
                )
                SpacerCustom(space = 4)
                Image(
                    painter = painterResource(id = R.drawable.ecotup_logo_png),
                    contentDescription = "logo_ecotup",
                    modifier = modifier
                        .size(100.dp, 20.dp)
                        .align(Alignment.CenterHorizontally)
                )
                SpacerCustom(space = 20)
                Box(modifier = modifier.align(Alignment.CenterHorizontally)) {
                    AsyncImage(
                        model = photoProfile,
                        contentDescription = "photo_driver",
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.profile_driver),
                        modifier = modifier
                            .size(114.dp)
                            .padding(2.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = "edit_icon_image",
                        modifier = modifier
                            .align(
                                Alignment.BottomEnd
                            )
                            .size(25.dp)
                    )
                }

                SpacerCustom(space = 5)
                Text(
                    text = "Your Picture", style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    ), modifier = modifier.align(Alignment.CenterHorizontally)
                )

                SpacerCustom(space = 10)

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
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = {
                        Text(
                            text = name,
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
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Name Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = GreenLight
                        )
                    }
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
                OutlinedTextField(value = email,
                    onValueChange = {
                        email = it
                        isEmailValid = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    },
                    placeholder = {
                        Text(
                            text = email,
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
                    leadingIcon = {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = "Email Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = GreenLight
                        )
                    })

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
                    value = phone,
                    onValueChange = {
                        phone = it
                    },
                    placeholder = {
                        Text(
                            text = phone,
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
                    leadingIcon = {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = "Phone Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = GreenLight
                        )
                    }
                )

                SpacerCustom(space = 10)
                // LICENSE
                Text(
                    text = "License",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = license,
                    onValueChange = {
                        license = it
                    },
                    placeholder = {
                        Text(
                            text = license,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    leadingIcon = {
                        Icon(
                            Icons.Default.LocalPolice,
                            contentDescription = "Phone Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = GreenLight
                        )
                    }
                )

                SpacerCustom(space = 10)
                Text(
                    text = "Vehicle",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(
                    value = type,
                    onValueChange = {
                        type = it
                    },
                    placeholder = {
                        Text(
                            text = type,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,

                        ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    leadingIcon = {
                        Icon(
                            Icons.Default.FireTruck,
                            contentDescription = "Vehicle Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = GreenLight
                        )
                    }
                )

                SpacerCustom(space = 20)

                // Button Next
                Button(modifier = modifier
                    .fillMaxWidth(), onClick = {
                    if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || license.isEmpty() || type.isEmpty()) {
                        sweetAlert(
                            context = context,
                            title = "Warning",
                            contentText = "Please fill all the form",
                            type = "warning", isCancel = true
                        )
                    } else {
                        if (phone.length in 11..13) {
                            updateProfileDriver(
                                id = idDriver,
                                email = email,
                                name = name,
                                phone = phone,
                                license = license,
                                type = type,
                                viewModel = viewModel,
                                lifecycleOwner = context as LifecycleOwner,
                                context = context,
                                navController = navController
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
                        text = "Save Changed", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, letterSpacing = 0.003.sp
                        )
                    )
                }

            }

        }
    }

}

private fun updateProfileDriver(
    id: String,
    email: String,
    name: String,
    phone: String,
    license: String,
    type: String,
    viewModel: EditDriverViewModel,
    lifecycleOwner: LifecycleOwner,
    context: Context,
    navController: NavController
) {
    runBlocking {
        viewModel.updateProfileDriver(id, email, name, phone, license, type)
            .observe(lifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Loading -> {
                            // IMPLEMENTASI LOADING
                        }

                        is Result.Success -> {
                            val error = result.data.error
                            val message = result.data.message

                            if (error == true) {
                                sweetAlert(context, "Error", message.toString(), "error")
                            } else {
                                sweetAlert(
                                    context,
                                    "Success",
                                    message.toString() + " Please refresh page !",
                                    "success"
                                )
                                navController.navigate(Screen.HomeScreenDriver.route)
                            }
                        }

                        is Result.Error -> {
                            sweetAlert(context, "Error", result.errorMessage.toString(), "error")
                        }
                    }

                }
            }
    }
}