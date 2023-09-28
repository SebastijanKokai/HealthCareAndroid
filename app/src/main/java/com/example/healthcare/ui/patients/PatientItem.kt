package com.example.healthcare.ui.patients

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.models.PatientData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries.localDate


@Composable
fun PatientItem(modifier: Modifier, patientData: PatientData) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
//        AsyncImage(
//            modifier = Modifier
//                .size(150.dp)
//                .clip(CircleShape),
//            model = patientData.photoUrl,
//            contentDescription = "Patient picture",
//            contentScale = ContentScale.Crop
//        )
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.")
        val formattedDateOfBirth: String? = patientData.dateOfBirth?.format(formatter)

        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            imageVector = Icons.Default.Person,
            contentDescription = ""
        )
        Column {
            Text(
                patientData.firstName + " " + patientData.lastName
            )
            if (formattedDateOfBirth != null) {
                Text(
                    formattedDateOfBirth
                )
            }
            if (patientData.illness != null) {
                Text(
                    patientData.illness
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPatientItem() {
    PatientItem(
        Modifier.padding(vertical = 8.dp),
        PatientData(
            id = "1",
            firstName = "Name1",
            lastName = "LastName1",
            photoUrl = "https://randomuser.me/api/portraits/med/women/57.jpg",
            illness = "Illness1",
            gender = null,
            address = null,
            dateOfBirth = LocalDate.now(),
            lastVisit = null,
            sleepingPattern = null,
            physicallyActive = false,
            smokingStatus = false,
            diagnosis = null,
            prescribedMedicationList = null
        )
    )
}