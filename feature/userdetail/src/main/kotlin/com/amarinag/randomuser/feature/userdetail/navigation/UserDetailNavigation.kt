package com.amarinag.randomuser.feature.userdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.amarinag.randomuser.feature.userdetail.UserDetailRoute

internal const val USERS_DETAILS_BASE = "users_details_route"
internal const val USER_ID_ARG = "userId"
const val USERS_DETAILS_ROUTE = "$USERS_DETAILS_BASE/{$USER_ID_ARG}"

fun NavController.navigateToUserDetail(userId: String) =
    navigate("${USERS_DETAILS_BASE}/$userId")

fun NavGraphBuilder.userDetailScreen() {
    composable(
        route = USERS_DETAILS_ROUTE,
        arguments = listOf(
            navArgument(USER_ID_ARG) { type = NavType.StringType }
        )
    ) {
        UserDetailRoute()
    }
}