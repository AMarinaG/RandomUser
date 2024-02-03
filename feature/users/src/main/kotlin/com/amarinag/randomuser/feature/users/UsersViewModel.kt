package com.amarinag.randomuser.feature.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor() : ViewModel() {
    val uiState: StateFlow<UsersState> = flow {
        emit(UsersState.Loading)
        kotlinx.coroutines.delay(3000)
        emit(UsersState.Users(listOf("user 1", "user 2")))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UsersState.Loading
    )
}