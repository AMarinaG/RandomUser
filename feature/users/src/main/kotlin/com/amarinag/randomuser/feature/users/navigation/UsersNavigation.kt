package com.amarinag.randomuser.feature.users.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amarinag.randomuser.feature.users.UsersRouter

const val USERS_ROUTE = "users_route"

fun NavGraphBuilder.usersScreen(onUserClick: (String) -> Unit) {
    composable(route = USERS_ROUTE) {
        UsersRouter(onUserClick = onUserClick)
    }
}
