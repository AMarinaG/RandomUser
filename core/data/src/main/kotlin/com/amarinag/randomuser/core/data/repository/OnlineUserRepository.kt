package com.amarinag.randomuser.core.data.repository

import com.amarinag.randomuser.core.data.model.asDomain
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.network.RandomUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnlineUserRepository @Inject constructor(
    private val network: RandomUserDataSource
) : UserRepository {
    override fun getUsers(): Flow<List<User>> = flow {
        emit(network.getUsers().users.asDomain())
    }
}