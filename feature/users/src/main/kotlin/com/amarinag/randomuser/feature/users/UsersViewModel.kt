package com.amarinag.randomuser.feature.users

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarinag.randomuser.core.common.result.Result.Error
import com.amarinag.randomuser.core.common.result.Result.Loading
import com.amarinag.randomuser.core.common.result.Result.Success
import com.amarinag.randomuser.core.common.result.asResult
import com.amarinag.randomuser.core.domain.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class UsersViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    val uiState: StateFlow<UsersState> = getUsersUseCase().asResult().mapLatest { result ->
        when (result) {
            is Error -> UsersState.Error
            Loading -> UsersState.Loading
            is Success -> UsersState.Users(result.data)
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UsersState.Loading
        )
}