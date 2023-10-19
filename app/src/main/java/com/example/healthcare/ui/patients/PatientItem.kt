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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.data.Gender
import com.example.healthcare.data.room.entities.PatientEntity
import java.time.format.DateTimeFormatter


@Composable
fun PatientItem(
    modifier: Modifier,
    patient: PatientEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.")
//        val formattedDateOfBirth: String? = patient.dateOfBirth?.format(formatter)

            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                imageVector = Icons.Default.Person,
                contentDescription = ""
            )
            Column {
                Text(
                    patient.firstName + " " + patient.lastName
                )
                if (patient.illness != null) {
                    Text(
                        patient.illness
                    )
                }
            }
            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(
                    onClick = {
                        onEdit()
                    }) {
                    Icon(
                        Icons.Default.Create,
                        contentDescription = "Edit patient",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = {
                        onDelete()
                    }) {
                    Icon(
                        Icons.Default.Inventory,
                        contentDescription = "Delete patient",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPatientItem() {
    PatientItem(
        Modifier.padding(vertical = 8.dp),
        PatientEntity(
            firstName = "Name1",
            lastName = "LastName1",
            photoUrl = "https://randomuser.me/api/portraits/med/women/57.jpg",
            illness = "Illness1",
            gender = Gender.OTHER,
            address = null,
            sleepingPattern = null,
            physicallyActive = false,
            smokingStatus = false,
            diagnosis = null,
        ),
        onEdit = {},
        onDelete = {}
    )
}