package com.ecotup.ecotupapplication.ui.general.login

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ecotup.ecotupapplication.R
import com.ecotup.ecotupapplication.data.vmf.ViewModelFactory
import com.ecotup.ecotupapplication.di.Injection
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.sweetAlert

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ), navController: NavController, modifier: Modifier = Modifier

) {
    val context = LocalContext.current

    Box(
        modifier = modifier.padding(16.dp)
    ) {
        // Background
        val imageBackground = painterResource(id = R.drawable.background_doodle)
        Image(painter = imageBackground, contentDescription = "Background Ecotup")

        // Button Back
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painterBack = painterResource(id = R.drawable.button_back)
            ClickableImage(painter = painterBack,
                contentDescription = "Back",
                onClick = { navController.popBackStack() })
            Text(
                text = "Back",
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }

        // Login Form
        LoginForm(modifier = modifier, context = context)

        // Ecotup Logo + Tagline
        Row(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {
            LogoEcotup(modifier = modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginForm(modifier: Modifier, context: Context) {
    var textEmail by remember {
        mutableStateOf("")
    }
    var isEmailValid by remember {
        mutableStateOf(false)
    }
    var textPassword by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SpacerCustom(space = 60)
        Box(
            modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Login", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 40.sp, color = GreenLight, fontWeight = FontWeight.Bold
                )
            )
        }
        SpacerCustom(space = 20)

        // Email
        Text(
            text = "Email", modifier = modifier, style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 15.sp, color = GreenLight, fontWeight = FontWeight.Bold
            )
        )
        SpacerCustom(space = 5)
        OutlinedTextField(value = textEmail,
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
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email Icon",
                    modifier = Modifier.padding(8.dp),
                    tint = GreenLight
                )
            })

        SpacerCustom(space = 10)

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
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Password Icon",
                    modifier = Modifier.padding(8.dp),
                    tint = GreenLight
                )
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword })
                {
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

        SpacerCustom(space = 20)

        // Button Login
        Button(modifier = modifier.fillMaxWidth(), onClick = {
            if (textEmail.isEmpty() || textPassword.isEmpty()) {
                sweetAlert(
                    context = context,
                    title = "Error",
                    contentText = "Login Failed",
                    type = "error",
                    isCancel = false
                )
            } else if (textEmail != "ecotup@gmail.com" || textPassword != "ecotup123") {
                sweetAlert(
                    context = context,
                    title = "Warning !",
                    contentText = "Login is not correct",
                    type = "warning",
                    isCancel = true
                )
            } else if (textEmail == "ecotup@gmail.com" && textPassword == "ecotup123") {
                sweetAlert(
                    context = context,
                    title = "Success",
                    contentText = "Your successful login",
                    type = "success",
                    isCancel = false
                )
            }

        }) {
            Text(
                text = "Login", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, letterSpacing = 0.003.sp
                )
            )
        }
    }
}

@Composable
fun ClickableImage(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .clickable { onClick() }
            .padding(8.dp)
    )
}

@Composable
private fun LogoEcotup(modifier: Modifier) {
    val imageEcotup = R.drawable.ecotup_logo_tagline_small
    val painterEcotup = painterResource(imageEcotup)
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterEcotup,
            contentDescription = "Logo Ecotup",
            modifier = modifier.width(250.dp)
        )
    }
}