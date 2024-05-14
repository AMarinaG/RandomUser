package com.amarinag.randomuser.feature.userdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amarinag.randomuser.feature.userdetail.UserDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailRoute(val userId: String)

fun NavController.navigateToUserDetail(userId: String) =
    navigate(UserDetailRoute(userId))

fun NavGraphBuilder.userDetailScreen() {
    composable<UserDetailRoute> {
        UserDetailRoute()
    }
}