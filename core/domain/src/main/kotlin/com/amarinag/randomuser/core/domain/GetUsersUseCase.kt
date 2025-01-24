package com.amarinag.randomuser.core.domain

import androidx.paging.PagingData
import com.amarinag.randomuser.core.data.repository.UserRepository
import com.amarinag.randomuser.core.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(params: Params): Flow<PagingData<User>> =
        userRepository.getUsers(params.query)

    data class Params(
        val query: String? = null
    )
}