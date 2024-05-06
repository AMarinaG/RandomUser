package com.amarinag.randomuser.feature.users

import androidx.compose.runtime.Stable
import com.amarinag.randomuser.core.model.User


@Stable
data class UsersState(
    val users: List<User>? = null,
    val isLoading: Boolean = false,
    val isLoadMore: Boolean = false,
    val filteredList: Boolean = false,
    val error: Boolean = false
)