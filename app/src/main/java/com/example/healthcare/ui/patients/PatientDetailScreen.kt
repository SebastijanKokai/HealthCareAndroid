package com.example.healthcare.ui.patients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.healthcare.data.fetchPatientRecords

@Composable
fun PatientDetailScreen(patientId: String) {
    val patientData = fetchPatientRecords().find {
       it.id == patientId
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (patientData?.photoUrl != null) {
            AsyncImage(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                model = patientData.photoUrl,
                contentDescription = "Profile picture",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text("Name: " + patientData?.firstName + " " + patientData?.lastName)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Gender: " + patientData?.gender)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Address: " + patientData?.address)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Date of birth: " + patientData?.dateOfBirth?.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Text("Last visit: " + patientData?.lastVisit?.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Text("Sleeping pattern: " + patientData?.sleepingPattern)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Physical activity: " + patientData?.physicallyActive)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Smoking status: " + patientData?.smokingStatus)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Illness: " + patientData?.illness)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Diagnosis: " + patientData?.diagnosis)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Prescribed medications: " + "Mocked medications")
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPatientDetailScreen() {
    PatientDetailScreen(
        patientId = "1"
    )
}