package com.example.healthcare.ui.login

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.R
import com.example.healthcare.models.login.LoginRequest
import com.example.healthcare.navigation.Screen
import com.example.healthcare.ui.common.ButtonWithIcon
import com.example.healthcare.ui.common.LoginField
import com.example.healthcare.ui.common.PasswordField
import com.example.healthcare.ui.theme.HealthCareTheme

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var loginRequest by remember { mutableStateOf(LoginRequest()) }
    val state by loginViewModel.state.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            loginViewModel.handleGoogleLoginResult(result)
        }
    )

    LaunchedEffect(key1 = loginViewModel.isLoggedIn()) {
        navController.navigate(Screen.HomeScreen.route) {
            this.popUpTo(Screen.LoginScreen.route) {
                this.inclusive = true
            }
        }
    }

    LaunchedEffect(key1 = state) {
        if (state.isLoginSuccessful) {
            Toast.makeText(
                context,
                "Login successful",
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(Screen.HomeScreen.route) {
                this.popUpTo(Screen.LoginScreen.route) {
                    this.inclusive = true
                }
            }
        } else if (state.loginError != null) {
            Toast.makeText(
                context,
                state.loginError,
                Toast.LENGTH_LONG
            ).show()
        }
        loginViewModel.resetState()
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
            onClick = {
                loginViewModel.googleLogin(launcher)
            }
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
        LoginScreen(rememberNavController(), hiltViewModel())
    }
}