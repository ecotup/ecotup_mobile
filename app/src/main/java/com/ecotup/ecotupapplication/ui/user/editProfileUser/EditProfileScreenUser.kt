package com.ecotup.ecotupapplication.ui.user.editProfileUser

//import com.ecotup.ecotupapplication.ui.general.login.LoginForm
//import com.ecotup.ecotupapplication.ui.navigation.Screen
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
import com.ecotup.ecotupapplication.data.model.PersonModelData
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
fun EditProfileScreenUser(
    viewModel: EditUserViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            LocalContext.current
        )
    ), modifier: Modifier = Modifier, navController: NavController
) {
    val context = LocalContext.current
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    var idUser by remember {
        mutableStateOf("")
    }

    viewModel.getSessionUser().observe(context as LifecycleOwner) {
        idUser = it.id
    }

    Box(modifier = modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp)) {
        // Button Back


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
            EditProfileForm(
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
fun EditProfileForm(
    viewModel: EditUserViewModel,
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
    var photoProfile by remember {
        mutableStateOf("")
    }
    var idUser by remember {
        mutableStateOf("")
    }

    LaunchedEffect(viewModel)
    {
        viewModel.getSessionUser().observe(lifecycleOwner) {
            idUser = it.id
            email = it.email
            name = it.name
            phone = it.phone
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
                    text = "MEMBER", style = MaterialTheme.typography.bodyMedium.copy(
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
                        contentDescription = "photo_user",
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.profile_temp),
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

                SpacerCustom(space = 20)
                // Button Save
                Button(modifier = modifier
                    .fillMaxWidth(), onClick = {
                    if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                        sweetAlert(
                            context,
                            "Error",
                            "Name, Email and Phone cannot be empty",
                            "error"
                        )
                    } else {
                        if (phone.length in 11..13) {
                            updateProfileUser(
                                id = idUser,
                                email = email,
                                name = name,
                                phone = phone,
                                viewModel = viewModel,
                                lifecycleOwner = context as LifecycleOwner,
                                context = context,
                                navController = navController
                            )
                        } else {
                            sweetAlert(
                                context,
                                "Error",
                                "Password and Password Confirmation not same",
                                "error"
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

private fun updateProfileUser(
    id: String,
    email: String,
    name: String,
    phone: String,
    viewModel: EditUserViewModel,
    lifecycleOwner: LifecycleOwner,
    context: Context,
    navController: NavController
) {
    runBlocking {
        viewModel.updateProfileUser(id, email, name, phone)
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
                                navController.navigate(Screen.HomeScreenUser.route)
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
