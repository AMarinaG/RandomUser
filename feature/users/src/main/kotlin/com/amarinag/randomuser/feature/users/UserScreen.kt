package com.amarinag.randomuser.feature.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amarinag.randomuser.core.designsystem.component.ImageTwoLinesItem
import com.amarinag.randomuser.core.designsystem.component.RandomTopAppBar
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.feature.users.UsersState.Error
import com.amarinag.randomuser.feature.users.UsersState.Loading
import com.amarinag.randomuser.feature.users.UsersState.Users

@Composable
internal fun UsersRouter(
    onUserClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UsersScreen(
        uiState = uiState,
        onUserClick = onUserClick,
        modifier = modifier
    )
}

@Composable
internal fun UsersScreen(
    uiState: UsersState,
    onUserClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            RandomTopAppBar(R.string.feature_users_contacts) {}
        }) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
        ) {
            when (uiState) {
                Error -> Text(text = "Error")
                Loading -> Text(text = "Loading")
                is Users -> UsersList(
                    users = uiState.users,
                    onUserClick = onUserClick,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun UsersList(users: List<User>, onUserClick: (String) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(users, key = { it.email }) { user ->
            ImageTwoLinesItem(
                title = "${user.name.first} ${user.name.last}",
                subtitle = user.email,
                imageUrl = user.picture.medium,
                onItemClick = onUserClick
            )
        }
    }
}

