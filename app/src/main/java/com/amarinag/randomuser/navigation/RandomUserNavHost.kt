package com.amarinag.randomuser.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.amarinag.randomuser.feature.users.navigation.USERS_ROUTE
import com.amarinag.randomuser.feature.users.navigation.usersScreen

@Composable
fun RandomUserNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = USERS_ROUTE,
        modifier = modifier
    ) {
        usersScreen(onUserClick = {})

    }
}