package com.example.healthcare.ui.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.models.register.RegisterRequest
import com.example.healthcare.navigation.Screen
import com.example.healthcare.ui.common.InputField
import com.example.healthcare.ui.common.PasswordField

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var registerRequest by remember { mutableStateOf(RegisterRequest()) }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state) {
        if (state.isRegisterSuccessful) {
            Toast.makeText(context, "Registered successfully.", Toast.LENGTH_LONG)
                .show()
            resetRegisterRequest(registerRequest)
            navController.navigate(Screen.Main.route)
        } else if (state.registerError != null) {
            Toast.makeText(context, state.registerError, Toast.LENGTH_LONG)
                .show()
        }
        viewModel.resetState()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = registerRequest.email,
            onChange = { data -> registerRequest = registerRequest.copy(email = data) },
            label = "E-mail",
            leadingIcon = @Composable {
                Icon(
                    Icons.Default.Mail,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
        PasswordField(
            modifier = Modifier.fillMaxWidth(),
            value = registerRequest.password,
            onChange = { data -> registerRequest = registerRequest.copy(password = data) },
            submit = {
                validateRequestThenRegisterUser(registerRequest, viewModel, context)
            }
        )
        Button(
            onClick = {
                validateRequestThenRegisterUser(registerRequest, viewModel, context)
            }, enabled = true, shape = RoundedCornerShape(5.dp), modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BasicText("Already registered?")
            ClickableText(modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = AnnotatedString("Login"),
                onClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                })
        }
    }

}

fun resetRegisterRequest(registerRequest: RegisterRequest) {
    registerRequest.email = ""
    registerRequest.password = ""
}

fun validateRequestThenRegisterUser(
    registerRequest: RegisterRequest,
    registerViewModel: RegisterViewModel,
    context: Context
) {
    if (registerRequest.isValid()) {
        registerViewModel.registerUser(registerRequest)
    } else {
        Toast.makeText(context, "Email or password are not in valid format.", Toast.LENGTH_LONG)
            .show()
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    val viewModel = viewModel<RegisterViewModel>()
    RegisterScreen(rememberNavController(), viewModel)
}