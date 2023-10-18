package com.example.healthcare.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.data.room.entities.PatientEntity
import com.example.healthcare.navigation.Screen
import com.example.healthcare.ui.patients.PatientItem
import com.example.healthcare.ui.patients.PatientState
import com.example.healthcare.ui.patients.PatientViewModel

@Composable
fun PatientsScreen(navController: NavController, viewModel: PatientViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val patientsState: PatientState by viewModel.patientResponse.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = patientsState) {
            is PatientState.Success ->
                PatientListScreen(navController = navController, patientsList = state.listOfPatients)

            is PatientState.Error -> Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()

            PatientState.Loading -> PatientsLoadingScreen()
        }
    }
}

@Composable
fun PatientListScreen(navController: NavController, patientsList: List<PatientEntity>) {

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(patientsList) {
            Box(modifier = Modifier.clickable {
                navController.navigate(
                    Screen.PatientDetailScreen.route.replace(
                        oldValue = "{patient_id}",
                        newValue = it.id.toString()
                    )
                )
            }) {
                PatientItem(patient = it, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.PatientEditScreen.route)
            },

            ) {
            Text(
                text = "Add patient"
            )
        }
    }

}

@Composable
fun PatientsLoadingScreen() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenListPreview() {
    PatientListScreen(rememberNavController(), emptyList())
}

@Preview(showBackground = true)
@Composable
fun HomeScreenLoadingPreview() {
    PatientsLoadingScreen()
}
