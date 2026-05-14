package com.example.nexvolunteer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(

        Routes.Home,
        Routes.Search,
        Routes.Chat,
        Routes.Profile
    )

    NavigationBar {

        val navBackStackEntry =
            navController.currentBackStackEntryAsState()

        val currentRoute =
            navBackStackEntry.value?.destination?.route

        items.forEach { screen ->

            NavigationBarItem(

                selected = currentRoute == screen.route,

                onClick = {

                    navController.navigate(screen.route)
                },

                icon = {

                    when (screen) {

                        is Routes.Home -> {
                            Icon(Icons.Default.Home, null)
                        }

                        is Routes.Search -> {
                            Icon(Icons.Default.Search, null)
                        }

                        is Routes.Chat -> {
                            Icon(Icons.Default.Chat, null)
                        }

                        is Routes.Profile -> {
                            Icon(Icons.Default.Person, null)
                        }

                        else -> {}
                    }
                }
            )
        }
    }
}