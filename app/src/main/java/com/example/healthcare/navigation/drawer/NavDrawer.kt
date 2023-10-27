package com.example.healthcare.navigation.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable

val menuItemList = listOf(
    MenuItem(
        id = "home",
        title = "Home",
        contentDescription = "Go to home",
        icon = Icons.Default.Home
    ),
    MenuItem(
        id = "profile",
        title = "Profile",
        contentDescription = "Go to profile",
        icon = Icons.Default.Person
    ),
    MenuItem(
        id = "logout",
        title = "Logout",
        contentDescription = "Press to logout",
        icon = Icons.Default.SubdirectoryArrowLeft
    ),
)

@Composable
fun NavDrawer(
    drawerState: DrawerState,
    onItemClick: (MenuItem) -> Unit,
    screenContent: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                Divider()
                DrawerBody(menuItems = menuItemList) {
                    onItemClick(it)
                }
            }
        }) {
        screenContent()
    }
}