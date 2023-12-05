package com.ecotup.ecotupapplication.ui.user.registerUser

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.di.Injection
import com.ecotup.ecotupapplication.ui.navigation.Screen
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.ClickableImageBack
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun RegisterUserScreenPassword(
    viewModel: RegisterUserViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ), navController: NavController, modifier: Modifier = Modifier
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
            RegisterPassword(modifier = modifier, context = context, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterPassword(modifier: Modifier, context: Context, navController: NavController) {
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
                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                    Text(
                        text = "Register", style = MaterialTheme.typography.bodyMedium.copy(
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