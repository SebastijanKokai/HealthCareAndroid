package com.example.healthcare.ui.login

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.models.login.RegisterRequest
import com.example.healthcare.navigation.Screen

@Composable
fun RegisterScreen(navController: NavController) {
    var registerRequest by remember { mutableStateOf(RegisterRequest()) }

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
                registerUser()
            },
            enabled = true,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth()
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
            ClickableText(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = AnnotatedString("Login"),
                onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                })
        }
    }

}

fun registerUser() {
    // TODO implement registration of user
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterScreen(rememberNavController())
}