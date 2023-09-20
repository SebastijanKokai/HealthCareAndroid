package com.example.healthcare.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.R
import com.example.healthcare.models.Credentials
import com.example.healthcare.navigation.Screen
import com.example.healthcare.ui.theme.HealthCareTheme

@Composable
fun LoginScreen(navController: NavController) {
    var credentials by remember { mutableStateOf(Credentials()) }
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(16.dp),
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
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = credentials.username,
            onValueChange = { data -> credentials = credentials.copy(username = data) },
            label = { Text("Username") },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = credentials.password,
            onValueChange = { data -> credentials = credentials.copy(password = data) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
        )
        Button(
            onClick = {
                authenticateUser(credentials, context, navController)
            },
            enabled = true,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

fun authenticateUser(credentials: Credentials, context: Context, navController: NavController) {
    if (isCredentialsValid(credentials)) {
        navController.navigate(Screen.MainScreen.route)
    } else {
        handleInvalidCredentials(context)
    }
}

fun handleInvalidCredentials(context: Context) {
    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
}

fun isCredentialsValid(credentials: Credentials): Boolean {
    return credentials.isNotEmpty()
            && credentials.username == "admin"
            && credentials.password == "1234"
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HealthCareTheme {
        LoginScreen(rememberNavController())
    }
}