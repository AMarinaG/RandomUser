package com.amarinag.randomuser.feature.users.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amarinag.randomuser.feature.users.UsersRouter
import kotlinx.serialization.Serializable

@Serializable
object UsersRoute

fun NavGraphBuilder.usersScreen(onUserClick: (String) -> Unit) {
    composable<UsersRoute> {
        UsersRouter(onUserClick = onUserClick)
    }
}
