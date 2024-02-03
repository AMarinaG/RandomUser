package com.amarinag.randomuser.feature.users

sealed interface UsersState {
    data object Loading : UsersState
    data class Users(val users: List<String>) : UsersState

    data object Error : UsersState
}