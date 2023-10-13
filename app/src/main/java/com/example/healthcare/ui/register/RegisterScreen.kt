package com.example.healthcare.ui.register

import android.content.Context
import android.text.TextUtils
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.models.register.RegisterRequest
import com.example.healthcare.navigation.Screen

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
            // TODO Login immediately
        } else if (state.registerError != null) {
            Toast.makeText(context, state.registerError, Toast.LENGTH_LONG)
                .show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = registerRequest.firstName,
//            onValueChange = { data -> registerRequest = registerRequest.copy(firstName = data) },
//            label = { Text("First Name") },
//        )
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = registerRequest.lastName,
//            onValueChange = { data -> registerRequest = registerRequest.copy(lastName = data) },
//            label = { Text("Last Name") },
//        )
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = registerRequest.contactNumber,
//            onValueChange = { data ->
//                registerRequest = registerRequest.copy(contactNumber = data)
//            },
//            label = { Text("Contact number") },
//        )
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = "",
//            onValueChange = { },
//            label = { Text("DateOfBirth") },
//        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = registerRequest.email,
            onValueChange = { data -> registerRequest = registerRequest.copy(email = data) },
            label = { Text("E-mail") },
        )
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = registerRequest.username,
//            onValueChange = { data -> registerRequest = registerRequest.copy(username = data) },
//            label = { Text("Username") },
//        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = registerRequest.password,
            onValueChange = { data -> registerRequest = registerRequest.copy(password = data) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
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
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                })
        }
    }

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