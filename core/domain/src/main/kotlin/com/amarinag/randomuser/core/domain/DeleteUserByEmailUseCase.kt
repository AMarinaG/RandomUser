package com.amarinag.randomuser.core.domain

import com.amarinag.randomuser.core.data.repository.UserRepository
import javax.inject.Inject

class DeleteUserByEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(params: Params) = userRepository.deleteUserByEmail(params.email)
    data class Params(
        val email: String
    )
}