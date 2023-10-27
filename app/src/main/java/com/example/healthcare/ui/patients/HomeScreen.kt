package com.example.healthcare.ui.patients

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.data.room.entities.PatientEntity
import com.example.healthcare.navigation.AUTH_GRAPH_ROUTE
import com.example.healthcare.navigation.NavDrawer
import com.example.healthcare.navigation.Screen
import com.example.healthcare.ui.common.Alert

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val patientsState: PatientState by viewModel.patientResponse.collectAsState()
    val isLoggedIn: Boolean by viewModel.isLoggedIn.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    if (!isLoggedIn) {
        navController.navigate(AUTH_GRAPH_ROUTE)
    }

    NavDrawer(
        drawerState = drawerState,
        onHome = {},
        onProfile = {
            navController.navigate(Screen.Profile.route)
        },
        onLogout = {
            viewModel.addAuthChangeListener()
            viewModel.logout()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val state = patientsState) {
                is PatientState.Success ->
                    PatientListScreen(
                        navController = navController,
                        patientsList = state.listOfPatients,
                        viewModel
                    )

                is PatientState.Error -> Toast.makeText(context, state.error, Toast.LENGTH_LONG)
                    .show()

                PatientState.Loading -> LoadingScreen()
            }
        }
    }
}

@Composable
fun PatientListScreen(
    navController: NavController,
    patientsList: List<PatientEntity>,
    viewModel: HomeScreenViewModel
) {
    var patientId by rememberSaveable {
        mutableStateOf("")
    }

    Alert(
        isDialogShown = patientId.isNotEmpty(),
        onDismissRequest = { patientId = "" },
        onConfirmation = {
            patientId = ""
            viewModel.deletePatient(patientId)
        },
        dialogTitle = "Are you sure?",
        dialogText = "Deleting a patient cannot be undone.",
        icon = Icons.Default.Warning
    )

    Box(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(patientsList) {
                Box(modifier = Modifier.clickable {
                    navController.navigate(
                        Screen.PatientDetail.route.replace(
                            oldValue = "{patient_id}",
                            newValue = it.id.toString()
                        )
                    )
                }) {
                    PatientItem(
                        patient = it,
                        modifier = Modifier.padding(vertical = 8.dp),
                        onEdit = {

                        },
                        onDelete = {
                            patientId = it.id.toString()
                        }
                    )
                }
            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.PatientEdit.route)
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.Add, "Button add patient")
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenListPreview() {
    PatientListScreen(rememberNavController(), emptyList(), hiltViewModel())
}

@Preview(showBackground = true)
@Composable
fun HomeScreenLoadingPreview() {
    LoadingScreen()
}
