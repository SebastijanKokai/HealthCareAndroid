package com.example.healthcare.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.data.fetchPatientRecords
import com.example.healthcare.navigation.Screen
import com.example.healthcare.ui.patients.PatientItem

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(fetchPatientRecords()) {
            Box(modifier = Modifier.clickable {
                navController.navigate(Screen.PatientDetailScreen.route.
                replace(
                    oldValue = "{patient_id}",
                    newValue = it.id
                ))
            }) {
                PatientItem(patient = it, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
