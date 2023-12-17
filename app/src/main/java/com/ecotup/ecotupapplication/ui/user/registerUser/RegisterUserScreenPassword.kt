package com.ecotup.ecotupapplication.ui.user.registerUser

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ButtonGoogle
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.sweetAlert
import kotlinx.coroutines.runBlocking

@Composable
fun RegisterUserScreenPassword(
    viewModel: RegisterUserViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    navController: NavController,
    name: String,
    email: String,
    phone: String,
    lat: Double,
    long: Double,
    modifier: Modifier = Modifier
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

            // Register Form Password
            RegisterPassword(
                modifier = modifier,
                context = context,
                navController = navController,
                viewModel = viewModel,
                name = name,
                email = email,
                phone = phone,
                lat = lat,
                long = long
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterPassword(
    modifier: Modifier,
    context: Context,
    navController: NavController,
    viewModel: RegisterUserViewModel,
    name: String,
    email: String,
    phone: String,
    lat: Double,
    long: Double
) {
    var textPassword by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var textPasswordConfirmation by remember {
        mutableStateOf("")
    }

    var showPasswordConfirmation by remember {
        mutableStateOf(false)
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
                            text = "User's security",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                SpacerCustom(space = 20)

                // Password
                Text(
                    text = "Password",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(value = textPassword,
                    onValueChange = {
                        textPassword = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your password",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            val imagePainter = if (showPassword) {
                                painterResource(id = R.drawable.visibility_off)
                            } else {
                                painterResource(id = R.drawable.visibility)
                            }
                            Image(
                                painter = imagePainter,
                                contentDescription = if (showPassword) "Show Password" else "Hide Password",
                            )

                        }
                    })

                SpacerCustom(space = 10)
                // Konfirmasi Password
                Text(
                    text = "Password Confirmation",
                    modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                    )
                )
                SpacerCustom(space = 5)
                OutlinedTextField(value = textPasswordConfirmation,
                    onValueChange = {
                        textPasswordConfirmation = it
                    },
                    placeholder = {
                        Text(
                            text = "Input your password confirmation",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                        )
                    },
                    visualTransformation = if (showPasswordConfirmation) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    trailingIcon = {
                        IconButton(onClick = {
                            showPasswordConfirmation = !showPasswordConfirmation
                        }) {
                            val imagePainter = if (showPassword) {
                                painterResource(id = R.drawable.visibility_off)
                            } else {
                                painterResource(id = R.drawable.visibility)
                            }
                            Image(
                                painter = imagePainter,
                                contentDescription = if (showPasswordConfirmation) "Show Password " else "Hide Password Confirmation",
                            )

                        }
                    })
                SpacerCustom(space = 15)
                TermsConditions(modifier = modifier)
                SpacerCustom(space = 20)

                // Button Register
                Button(modifier = modifier.fillMaxWidth(), onClick = {
                    if(textPassword.isEmpty() || textPasswordConfirmation.isEmpty()){
                        sweetAlert(context, "Error", "Password and Password Confirmation cannot be empty", "error")
                    } else {
                        if (textPassword == textPasswordConfirmation) {
                            setRegisterUser(
                                name = name,
                                password = textPassword,
                                email = email,
                                phone = phone,
                                latitude = lat,
                                longitude = long,
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
                        text = "Register", style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold, letterSpacing = 0.003.sp
                        )
                    )
                }
            }
            SpacerCustom(space = 20)
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

@Composable
private fun TermsConditions(modifier: Modifier) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = isChecked, onCheckedChange = {
            isChecked = it
        }, modifier = modifier.size(20.dp))
        SpacerCustom(space = 10)
        Text(
            text = "I read and understand all the terms and conditions that apply to this application",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp,
                color = GreenLight,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Justify
            )
        )
    }
}


private fun setRegisterUser(
    name: String,
    password: String,
    email: String,
    phone: String,
    latitude: Double,
    longitude: Double,
    viewModel: RegisterUserViewModel,
    lifecycleOwner: LifecycleOwner,
    context: Context,
    navController: NavController
) {
    runBlocking {
        viewModel.setRegisterUser(name, password, email, phone, latitude, longitude)
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
                                sweetAlert(context, "Success", message.toString(), "success")
                                navController.navigate(Screen.LoginScreen.route)
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