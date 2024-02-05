package com.amarinag.randomuser.feature.userdetail

import com.amarinag.randomuser.core.model.User

sealed interface UserDetailUiState {
    data object Loading : UserDetailUiState
    data class Success(val user: User) : UserDetailUiState

    data object Error : UserDetailUiState
}