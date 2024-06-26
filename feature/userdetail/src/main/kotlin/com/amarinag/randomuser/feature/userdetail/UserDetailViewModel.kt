package com.amarinag.randomuser.feature.userdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.amarinag.randomuser.core.common.result.Result.Error
import com.amarinag.randomuser.core.common.result.Result.Loading
import com.amarinag.randomuser.core.common.result.Result.Success
import com.amarinag.randomuser.core.common.result.asResult
import com.amarinag.randomuser.core.domain.GetUserDetailUseCase
import com.amarinag.randomuser.core.domain.GetUserDetailUseCase.Params
import com.amarinag.randomuser.feature.userdetail.navigation.UserDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {
    private val userDetailRoute: UserDetailRoute = savedStateHandle.toRoute()
    val uiState: StateFlow<UserDetailUiState> =
        getUserDetailUseCase(Params(userDetailRoute.userId))
            .asResult()
            .mapLatest { result ->
                when (result) {
                    is Error -> UserDetailUiState.Error
                    Loading -> UserDetailUiState.Loading
                    is Success -> UserDetailUiState.Success(result.data)
                }

            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UserDetailUiState.Loading
            )
}