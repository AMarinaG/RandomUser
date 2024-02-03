package com.amarinag.randomuser.feature.users

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    Column(modifier = modifier) {
        when (uiState) {
            Error -> Text(text = "Error")
            Loading -> Text(text = "Loading")
            is Users -> Text(text = uiState.users.map { it.phone }.toString())
        }
    }
}