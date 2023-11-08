package com.example.healthcare

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.healthcare.navigation.AUTH_GRAPH_ROUTE
import com.example.healthcare.navigation.DRAWER_GRAPH_ROUTE
import com.example.healthcare.navigation.Screen
import com.example.healthcare.navigation.drawer.NavDrawer
import com.example.healthcare.navigation.drawerNavGraph
import com.example.healthcare.ui.patients.HomeScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable(
    parentNavController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (drawerState.isOpen) {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        } else {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->


        NavDrawer(
            drawerState = drawerState,
            onItemClick = { item ->
                coroutineScope.launch {
                    drawerState.close()
                }

                when (item.id) {
                    "home" -> {
                        navController.navigate(Screen.Home.route)
                    }

                    "profile" -> {
                        navController.navigate(Screen.Profile.route)
                    }

                    "logout" -> {
                        coroutineScope.launch {
                            viewModel.logout()
                            // delay added as a quick-fix, otherwise getCurrentUser returns non null and returns user back to the HomeScreen
                            delay(1000)
                            parentNavController.navigate(AUTH_GRAPH_ROUTE)
                        }
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = DRAWER_GRAPH_ROUTE,
            ) {
                drawerNavGraph(parentNavController)
            }
        }
    }
}