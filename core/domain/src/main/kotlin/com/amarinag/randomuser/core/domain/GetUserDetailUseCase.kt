package com.amarinag.randomuser.core.domain

import com.amarinag.randomuser.core.data.repository.UserRepository
import com.amarinag.randomuser.core.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(params: Params): Flow<User> = userRepository.getUserByEmail(params.email)

    data class Params(
        val email: String
    )
}