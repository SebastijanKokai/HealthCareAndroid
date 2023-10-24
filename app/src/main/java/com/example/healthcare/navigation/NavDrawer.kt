package com.example.healthcare.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.healthcare.R

@Composable
fun NavDrawer(
    drawerState: DrawerState,
    onHome: () -> Unit,
    onProfile: () -> Unit,
    onLogout: () -> Unit,
    screenContent: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painterResource(id = R.drawable.logo),
                        contentDescription = "Logout",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text("Healthcare App", modifier = Modifier.padding(16.dp))
                }
                Divider()
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    label = {
                        Text(text = "Home")
                    },
                    selected = false,
                    onClick = { onHome() }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    label = {
                        Text(text = "Profile")
                    },
                    selected = false,
                    onClick = { onProfile() }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Logout",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    label = {
                        Text(text = "Logout")
                    },
                    selected = false,
                    onClick = { onLogout() }
                )
            }
        }) {
        screenContent()
    }
}