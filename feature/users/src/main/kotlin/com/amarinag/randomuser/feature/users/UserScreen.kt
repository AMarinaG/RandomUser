package com.amarinag.randomuser.feature.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amarinag.randomuser.core.designsystem.component.ImageTwoLinesItem
import com.amarinag.randomuser.core.designsystem.component.RandomTopAppBar
import com.amarinag.randomuser.core.designsystem.theme.spacing
import com.amarinag.randomuser.core.model.User

@Composable
internal fun UsersRouter(
    onUserClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(null) {
        viewModel.getUsers()
    }
    UsersScreen(
        uiState = uiState,
        onUserClick = onUserClick,
        loadMoreUsers = { viewModel.getUsers(true) },
        queryFilter = viewModel::queryFilter,
        modifier = modifier
    )
}

@Composable
internal fun UsersScreen(
    uiState: UsersState,
    onUserClick: (String) -> Unit,
    loadMoreUsers: () -> Unit,
    queryFilter: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    Scaffold(
        modifier = modifier,
        topBar = {
            RandomTopAppBar(
                R.string.feature_users_contacts,
                query = query,
                isSearchActive = isSearchActive,
                onSearch = queryFilter,
                onActiveChange = { isSearchActive = it },
                onQueryChange = {
                    query = it
                    queryFilter(it)
                },
                onSearchClick = { isSearchActive = !isSearchActive }
            )
        }) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
        ) {
            if (uiState.error) {
                Text(
                    text = stringResource(id = R.string.feature_users_error),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (uiState.isLoading) {
                Text(text = stringResource(id = R.string.feature_users_loading))
            }
            if (!uiState.users.isNullOrEmpty()) {
                UsersList(
                    users = uiState.users,
                    onUserClick = onUserClick,
                    loadMoreUsers = loadMoreUsers,
                    isLoadingMore = uiState.isLoadMore,
                    isFilteredList = uiState.filteredList,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun UsersList(
    users: List<User>,
    isLoadingMore: Boolean,
    isFilteredList: Boolean,
    onUserClick: (String) -> Unit,
    loadMoreUsers: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(scrollState.canScrollForward) {
        if (!scrollState.canScrollForward && !isLoadingMore && !isFilteredList) {
            loadMoreUsers()
        }
    }
    LazyColumn(modifier = modifier, state = scrollState) {
        items(users, key = { it.email }) { user ->
            ImageTwoLinesItem(
                title = user.name.fullname,
                subtitle = user.email,
                imageUrl = user.picture.medium,
                onItemClick = onUserClick
            )
        }
        if (!isFilteredList) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.normal)
                ) {
                    Text(text = "Loading")
                }
            }
        }
    }
}

