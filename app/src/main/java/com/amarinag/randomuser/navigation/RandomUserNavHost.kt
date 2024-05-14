package com.amarinag.randomuser.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.amarinag.randomuser.feature.userdetail.navigation.navigateToUserDetail
import com.amarinag.randomuser.feature.userdetail.navigation.userDetailScreen
import com.amarinag.randomuser.feature.users.navigation.UsersRoute
import com.amarinag.randomuser.feature.users.navigation.usersScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RandomUserNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = UsersRoute,
            modifier = modifier
        ) {
            usersScreen(onUserClick = { navController.navigateToUserDetail(it) })
            userDetailScreen()

        }
    }
}