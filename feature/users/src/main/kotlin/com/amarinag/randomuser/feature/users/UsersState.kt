package com.amarinag.randomuser.feature.users

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.amarinag.randomuser.core.model.User

@Stable
data class UsersState(
    val users: PagingData<User>? = null,
    val isLoading: Boolean = false,
    val isLoadMore: Boolean = false,
    val filteredList: Boolean = false,
    val error: Boolean = false
)