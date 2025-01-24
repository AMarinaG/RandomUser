package com.amarinag.randomuser.feature.users

import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.amarinag.randomuser.core.designsystem.component.ImageThreeLinesItem
import com.amarinag.randomuser.core.designsystem.component.ImageThreeLinesItemPlaceholder
import com.amarinag.randomuser.core.designsystem.component.ImageTwoLinesItem
import com.amarinag.randomuser.core.designsystem.component.ImageTwoLinesItemPlaceholder
import com.amarinag.randomuser.core.designsystem.component.RandomTopAppBar
import com.amarinag.randomuser.core.model.User
import java.util.UUID

const val UserListTestTag = "UserListTestTag"

@Composable
internal fun UsersRouter(
    onUserClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsLazyPagingItems()
    UsersScreen(
        uiState = uiState,
        onUserClick = onUserClick,
        onDeleteUser = viewModel::onDeleteUser,
        queryFilter = viewModel::queryFilter,
        modifier = modifier
    )
}

@Composable
internal fun UsersScreen(
    uiState: LazyPagingItems<User>,
    onUserClick: (String) -> Unit,
    onDeleteUser: (User) -> Unit,
    queryFilter: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val (showPhone, onShowPhone) = remember { mutableStateOf(false) }
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
                onSearchClick = { isSearchActive = !isSearchActive },
                showPhone = showPhone,
                onShowPhoneIcon = onShowPhone
            )
        }) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
        ) {
            when {
                uiState.loadState.refresh is LoadState.Loading && uiState.itemCount == 0 -> {
                    Text(text = stringResource(id = R.string.feature_users_loading))
                }

                uiState.loadState.refresh is LoadState.NotLoading && uiState.itemCount == 0 -> {
                    Text(text = stringResource(R.string.feature_users_initial_load))
                }

                uiState.loadState.hasError -> {
                    Text(
                        text = stringResource(id = R.string.feature_users_error),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                else -> {
                    UsersList(
                        users = uiState,
                        onUserClick = onUserClick,
                        onDeleteUser = onDeleteUser,
                        isFilteredList = query.isNotBlank(),
                        showPhone = showPhone,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersList(
    users: LazyPagingItems<User>,
    isFilteredList: Boolean,
    onUserClick: (String) -> Unit,
    onDeleteUser: (User) -> Unit,
    modifier: Modifier = Modifier,
    showPhone: Boolean = false
) {
    LazyColumn(modifier = modifier.testTag(UserListTestTag)) {
        items(
            count = users.itemCount,
            key = { users[it]?.email ?: UUID.randomUUID().toString() }) { index ->
            users[index]?.let { user ->
                if (showPhone) {
                    ImageThreeLinesItem(
                        title = user.name.fullname,
                        subtitle = user.email,
                        label = user.phone,
                        imageUrl = user.picture.medium,
                        onItemClick = { onUserClick(user.email) },
                        onItemDelete = { onDeleteUser(user) },
                        modifier = Modifier.animateItem(
                            fadeInSpec = spring(),
                            fadeOutSpec = spring()
                        )
                    )
                } else {
                    ImageTwoLinesItem(
                        title = user.name.fullname,
                        subtitle = user.email,
                        imageUrl = user.picture.medium,
                        onItemClick = { onUserClick(user.email) },
                        onItemDelete = { onDeleteUser(user) },
                        modifier = Modifier.animateItem(
                            fadeInSpec = tween(),
                            fadeOutSpec = tween()
                        )
                    )
                }
            }
        }
        if (!isFilteredList || users.loadState.refresh == LoadState.Loading) {
            item {
                if (showPhone) {
                    ImageThreeLinesItemPlaceholder()
                } else {
                    ImageTwoLinesItemPlaceholder()
                }
            }
        }
    }
}

