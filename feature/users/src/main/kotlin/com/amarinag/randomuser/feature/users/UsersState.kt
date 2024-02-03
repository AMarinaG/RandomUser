package com.amarinag.randomuser.feature.users

import com.amarinag.randomuser.core.model.User

sealed interface UsersState {
    data object Loading : UsersState
    data class Users(val users: List<User>) : UsersState

    data object Error : UsersState
}