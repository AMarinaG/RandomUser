package com.amarinag.randomuser.feature.users

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amarinag.randomuser.core.designsystem.component.ImageThreeLinesItem
import com.amarinag.randomuser.core.designsystem.component.ImageThreeLinesItemPlaceholder
import com.amarinag.randomuser.core.designsystem.component.ImageTwoLinesItem
import com.amarinag.randomuser.core.designsystem.component.ImageTwoLinesItemPlaceholder
import com.amarinag.randomuser.core.designsystem.component.RandomTopAppBar
import com.amarinag.randomuser.core.model.User

const val UserListTestTag = "UserListTestTag"

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
        onDeleteUser = viewModel::onDeleteUser,
        loadMoreUsers = { viewModel.getUsers(true) },
        queryFilter = viewModel::queryFilter,
        modifier = modifier
    )
}

@Composable
internal fun UsersScreen(
    uiState: UsersState,
    onUserClick: (String) -> Unit,
    onDeleteUser: (User) -> Unit,
    loadMoreUsers: () -> Unit,
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
                    onDeleteUser = onDeleteUser,
                    loadMoreUsers = loadMoreUsers,
                    isLoadingMore = uiState.isLoadMore,
                    isFilteredList = uiState.filteredList,
                    showPhone = showPhone,
                    modifier = modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersList(
    users: List<User>,
    isLoadingMore: Boolean,
    isFilteredList: Boolean,
    onUserClick: (String) -> Unit,
    onDeleteUser: (User) -> Unit,
    loadMoreUsers: () -> Unit,
    modifier: Modifier = Modifier,
    showPhone: Boolean = false
) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(scrollState.canScrollForward) {
        if (!scrollState.canScrollForward && !isLoadingMore && !isFilteredList) {
            loadMoreUsers()
        }
    }
    LazyColumn(modifier = modifier.testTag(UserListTestTag), state = scrollState) {
        items(users, key = { it.email }) { user ->
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
        if (!isFilteredList) {
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

