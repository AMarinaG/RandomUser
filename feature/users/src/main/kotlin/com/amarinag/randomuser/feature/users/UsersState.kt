package com.amarinag.randomuser.feature.users

import com.amarinag.randomuser.core.model.User

data class UsersState(
    val users: List<User>? = null,
    val isLoading: Boolean = false,
    val isLoadMore: Boolean = false,
    val error: Boolean = false
)