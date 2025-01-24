package com.amarinag.randomuser.feature.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.amarinag.randomuser.core.domain.DeleteUserByEmailUseCase
import com.amarinag.randomuser.core.domain.DeleteUserByEmailUseCase.Params
import com.amarinag.randomuser.core.domain.GetUsersUseCase
import com.amarinag.randomuser.core.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserByEmailUseCase: DeleteUserByEmailUseCase
) : ViewModel() {
    private val queryFlow: MutableStateFlow<String> = MutableStateFlow("")
    val uiState: Flow<PagingData<User>> =
        queryFlow.debounce(500).flatMapLatest { query ->
            getUsersUseCase(GetUsersUseCase.Params(query))
        }.cachedIn(viewModelScope)

    fun queryFilter(query: String) {
        queryFlow.update { query }
    }

    fun onDeleteUser(user: User) {
        viewModelScope.launch {
            deleteUserByEmailUseCase(Params(user.email))
        }
    }
}