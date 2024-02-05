package com.amarinag.randomuser.feature.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarinag.randomuser.core.common.result.Result.Error
import com.amarinag.randomuser.core.common.result.Result.Loading
import com.amarinag.randomuser.core.common.result.Result.Success
import com.amarinag.randomuser.core.common.result.asResult
import com.amarinag.randomuser.core.domain.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UsersState> =
        MutableStateFlow(UsersState(null, isLoading = true))
    val uiState: StateFlow<UsersState> = _uiState.asStateFlow()

    fun getUsers(loadMore: Boolean = false) {
        viewModelScope.launch {
            getUsersUseCase().asResult().mapLatest { result ->
                when (result) {
                    is Error -> _uiState.update { it.copy(error = true) }
                    Loading -> _uiState.update {
                        if (loadMore) {
                            it.copy(isLoadMore = true)
                        } else {
                            it.copy(isLoading = true)
                        }
                    }

                    is Success -> _uiState.update {
                        it.copy(
                            users = result.data,
                            isLoading = false,
                            isLoadMore = false
                        )
                    }
                }
            }.collect()
        }
    }
}