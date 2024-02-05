package com.amarinag.randomuser.core.data.repository

import android.util.Log
import com.amarinag.randomuser.core.data.model.asDomain
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.network.RandomUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

private const val INITIAL_PAGE = 0

@Singleton
class OnlineUserRepository @Inject constructor(
    private val network: RandomUserDataSource
) : UserRepository {
    private var seed: String? = null
    private var currentPage: Int = INITIAL_PAGE
    private val users: MutableList<User> = mutableListOf()
    override fun getUsers(): Flow<List<User>> = flow {
        currentPage = currentPage.plus(1)
        val response = network.getUsers(currentPage, seed)
        seed = response.info.seed
        users.addAll(response.users.asDomain())
        emit(users)
    }

    override fun getUserByEmail(email: String): Flow<User> =
        flowOf(users.first { it.email == email })
}