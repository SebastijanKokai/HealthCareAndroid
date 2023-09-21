package com.example.healthcare.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.R
import com.example.healthcare.models.login.LoginRequest
import com.example.healthcare.navigation.Screen
import com.example.healthcare.models.login.SignInState
import com.example.healthcare.ui.common.ButtonWithIcon
import com.example.healthcare.ui.common.LoginField
import com.example.healthcare.ui.common.PasswordField
import com.example.healthcare.ui.theme.HealthCareTheme

@Composable
fun LoginScreen(
    navController: NavController,
    state: SignInState,
    onSignInClick: () -> Unit,
    resetState: () -> Unit
) {
    var loginRequest by remember { mutableStateOf(LoginRequest()) }

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(context, "Successfully logged in", Toast.LENGTH_LONG).show()
            navController.navigate(Screen.ProfileScreen.route)
            resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
        )
        LoginField(
            modifier = Modifier.fillMaxWidth(),
            value = loginRequest.username,
            onChange = { data -> loginRequest = loginRequest.copy(username = data) },
        )
        PasswordField(
            modifier = Modifier.fillMaxWidth(),
            value = loginRequest.password,
            onChange = { data -> loginRequest = loginRequest.copy(password = data) },
            submit = {
                authenticateUser(loginRequest, context, navController)
            }
        )
        Button(
            onClick = {
                authenticateUser(loginRequest, context, navController)
            },
            enabled = true,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.size(8.dp))
        ButtonWithIcon(
            imageId = R.drawable.ic_google,
            buttonText = "Login with Google",
            fontColor = Color.White,
            onClick = { onSignInClick() }
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BasicText("Not registered?")
            ClickableText(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = AnnotatedString("Sign up"),
                onClick = {
                    navController.navigate(Screen.RegisterScreen.route)
                })
        }
    }
}

fun authenticateUser(loginRequest: LoginRequest, context: Context, navController: NavController) {
    if (isCredentialsValid(loginRequest)) {
        navController.navigate(Screen.HomeScreen.route)
    } else {
        handleInvalidCredentials(context)
    }
}

fun handleInvalidCredentials(context: Context) {
    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
}

fun isCredentialsValid(loginRequest: LoginRequest): Boolean {
    return loginRequest.isNotEmpty()
            && loginRequest.username == "admin"
            && loginRequest.password == "1234"
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HealthCareTheme {
        LoginScreen(rememberNavController(), SignInState(), {}, {})
    }
}